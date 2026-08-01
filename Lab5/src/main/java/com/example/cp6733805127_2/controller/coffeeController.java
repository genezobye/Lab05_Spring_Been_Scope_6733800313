package com.example.cp6733805127_2.controller;

import com.example.cp6733805127_2.model.Coffee;
import com.example.cp6733805127_2.service.coffeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coffees")
public class coffeeController {

    private final coffeeService coffeeService;

    public coffeeController(coffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    // GET /coffees  
    @GetMapping
    public List<Coffee> getAllCoffees() {
        return coffeeService.getAll();
    }

    // GET /coffees/search?name=...  
    @GetMapping("/search")
    public List<Coffee> searchCoffees(@RequestParam String name) {
        return coffeeService.searchByName(name);
    }

    // GET /coffees/{id}  
    @GetMapping("/{id}")
    public ResponseEntity<Coffee> getCoffeeById(@PathVariable Long id) {
        return coffeeService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST   
    @PostMapping
    public ResponseEntity<Coffee> addCoffee(@RequestBody Coffee coffee) {
        Coffee created = coffeeService.add(coffee);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT 
    @PutMapping("/{id}")
    public ResponseEntity<Coffee> updateCoffee(@PathVariable Long id, @RequestBody Coffee coffee) {
        return coffeeService.update(id, coffee)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoffee(@PathVariable Long id) {
        boolean removed = coffeeService.delete(id);
        return removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}

