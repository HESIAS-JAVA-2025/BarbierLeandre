package org.example.rest;

import jakarta.validation.Valid;
import org.example.entities.Garage;
import org.example.rest.mappers.GarageMapper;
import org.example.services.CarService;
import org.example.services.GarageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Controller
@RequestMapping("/api/garages")
public class GarageController {

    @Autowired
    public GarageService service;

    @Autowired
    public CarService carService;

    @PostMapping("/create")
    public ResponseEntity createGarage(@RequestBody @Valid Garage garage) {
        try {
            var newGarage = service.createGarage(garage);
            return ResponseEntity
                    .created(new URI("/api/garages" + newGarage.getId()))
                    .body(newGarage);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getAllGarages() {
        try {
            var garages = service.getGarages();
            return ResponseEntity.ok(GarageMapper.toDto(garages));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneGarage(@PathVariable("id") Long id) {
        try {
            var garage = service.getGarageById(id);
            return ResponseEntity.ok(GarageMapper.toDto(garage.get()));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @GetMapping("/addCar/{idGarage}/{idCar}")
    public ResponseEntity addCar(@PathVariable("idGarage") Long idG, @PathVariable("idCar") Long idC) {
        try {
            var car = carService.getCarById(idC).get();
            var garage = service.getGarageById(idG).get();

            garage.getCars().add(car);
            car.setGarage(garage);

            return ResponseEntity.ok(GarageMapper.toDto(garage));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @GetMapping("/removeCar/{idGarage}/{idCar}")
    public ResponseEntity removeCar(@PathVariable("idGarage") Long idG, @PathVariable("idCar") Long idC) {
        try {
            var car = carService.getCarById(idC).get();
            var garage = service.getGarageById(idG).get();

            garage.getCars().remove(car);
            car.setGarage(null);

            return ResponseEntity.ok(GarageMapper.toDto(garage));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }
}
