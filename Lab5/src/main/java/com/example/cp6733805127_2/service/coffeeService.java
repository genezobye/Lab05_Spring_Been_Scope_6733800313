package com.example.cp6733805127_2.service;

import com.example.cp6733805127_2.model.Coffee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class coffeeService {

    private final List<Coffee> coffees = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong();

    
    public coffeeService() {
        coffees.add(new Coffee(nextId.incrementAndGet(), "Espresso", 45.0));
        coffees.add(new Coffee(nextId.incrementAndGet(), "Latte", 55.0));
    }

    public List<Coffee> getAll() {
        return coffees;
    }

    public Optional<Coffee> getById(Long id) {
        return coffees.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    public Coffee add(Coffee coffee) {
        coffee.setId(nextId.incrementAndGet());
        coffees.add(coffee);
        return coffee;
    }

    public Optional<Coffee> update(Long id, Coffee updated) {
        return getById(id).map(existing -> {
            existing.setName(updated.getName());
            existing.setPrice(updated.getPrice());
            return existing;
        });
    }

    public boolean delete(Long id) {
        return coffees.removeIf(c -> c.getId().equals(id));
    }

    public List<Coffee> searchByName(String name) {
        return coffees.stream()
                .filter(c -> c.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }
}

