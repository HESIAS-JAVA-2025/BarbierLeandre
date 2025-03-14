package org.example.rest.mappers;

import org.example.entities.Car;
import org.example.rest.dtos.CarDTO;

import java.util.List;

public class CarMapper {

    public static CarDTO toDto(Car car) {
        var dto = new CarDTO();
        dto.setId((car.getId()));
        dto.setPower(car.getPower());
        dto.setModel(car.getModel());
        return dto;
    }

    public static Car toEntity(CarDTO dto) {
        var car = new Car();
        car.setId((dto.getId()));
        car.setPower(dto.getPower());
        car.setModel(dto.getModel());
        return car;
    }

    public static List<CarDTO> toDto(List<Car> cars) {
        return cars.stream().map(CarMapper::toDto).toList();
    }

    public static List<Car> toEntity(List<CarDTO> cars) {
        return cars.stream().map(CarMapper::toEntity).toList();
    }
}
