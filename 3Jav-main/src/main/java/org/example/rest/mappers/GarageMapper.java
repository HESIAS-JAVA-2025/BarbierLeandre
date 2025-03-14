package org.example.rest.mappers;


import org.example.entities.Garage;
import org.example.rest.dtos.GarageDTO;

import java.util.List;

public class GarageMapper {
    public static GarageDTO toDto(Garage garage) {
        var dto = new GarageDTO();
        dto.setId((garage.getId()));
        dto.setName(garage.getName());
        dto.setAddress(garage.getAddress());
        dto.setCars(CarMapper.toDto(garage.getCars()));
        return dto;
    }

    public static Garage toEntity(GarageDTO dto) {
        var garage = new Garage();
        garage.setId((dto.getId()));
        garage.setName(dto.getName());
        garage.setAddress(dto.getAddress());
        garage.setCars(CarMapper.toEntity(dto.getCars()));
        return garage;
    }

    public static List<GarageDTO> toDto(List<Garage> garages) {
        return garages.stream().map(GarageMapper::toDto).toList();
    }

    public static List<Garage> toEntity(List<GarageDTO> garages) {
        return garages.stream().map(GarageMapper::toEntity).toList();
    }

}
