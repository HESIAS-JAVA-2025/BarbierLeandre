package org.example.services;

import org.example.entities.Car;
import org.example.repos.CarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository repository;

    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    public Car createCar(Car c) {
        return repository.save(c);
    }

    public Optional<Car> getCarById(Long id) {
        return repository.findById(id);
    }

    public List<Car> getCars() {
        return toList(repository.findAll());
    }

    public void deleteCar(Car c) {
        repository.delete(c);
    }

    public static List<Car> toList(Iterable<Car> cars) {
        List<Car> result = new ArrayList<Car>();
        cars.forEach(result::add);
        return result;
    }

    public void deletePower(Car power){
        repository.delete(power);
    }

    //public Optional<Car> updateCar(Car Car) {
        //return CarRepository.save(Car);
    //}
}
