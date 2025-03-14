package org.example.rest;

import jakarta.validation.Valid;
import org.example.entities.Car;
import org.example.rest.dtos.CarDTO;
import org.example.rest.mappers.CarMapper;
import org.example.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@Controller
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService service;

    @PostMapping("/create")
    public ResponseEntity createCar(@RequestBody @Valid Car car) {
        try {
            var newCar = service.createCar(car);
            return ResponseEntity
                    .created(new URI("/api/cars/" + newCar.getId()))
                    .body(CarMapper.toDto(newCar));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getAllCars() {
        try {
            var cars = service.getCars();
            return ResponseEntity.ok(CarMapper.toDto(cars));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneCar(@PathVariable("id") Long id) {
        try {
            Optional<Car> car = service.getCarById(id);
            return car.map(value -> ResponseEntity.ok(CarMapper.toDto(value)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCar(@PathVariable Long id) {
        return service.getCarById(id)
                .map(car -> {
                    service.deleteCar(car);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/power/{id}")
    public ResponseEntity deleteCarPower(@PathVariable Long id) {
        return service.getCarById(id)
                .map(car -> {
                    service.deletePower(car);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //@PutMapping("/{id}")
    //    public ResponseEntity<CarDTO> update(@PathVariable Long id, @RequestBody CarDTO CarDTO) {
    //        var Car = CarMapper.toEntity(CarDTO);
    //        Car.setId(id);
    //        var bookUpdated = CarService.updateCar(Car);
    //        return ResponseEntity.ok(CarMapper.toDto(CarUpdated));
    //    }
}
