package org.example.services;

import org.example.entities.Garage;
import org.example.repos.GarageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GarageService {

    private final GarageRepository repository;

    public GarageService(GarageRepository repository) {
        this.repository = repository;
    }

    public Garage createGarage(Garage g) {
        return repository.save(g);
    }

    public Optional<Garage> getGarageById(Long id) {
        return repository.findById(id);
    }

    public List<Garage> getGarages() {
        return toList(repository.findAll());
    }

    public void deleteGarage(Garage g) {
        repository.delete(g);
    }

    public static List<Garage> toList(Iterable<Garage> garages) {
        List<Garage> result = new ArrayList<Garage>();
        garages.forEach(result::add);
        return result;
    }
}
