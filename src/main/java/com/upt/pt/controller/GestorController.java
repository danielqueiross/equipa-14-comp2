package com.upt.pt.controller;

import com.upt.pt.entity.Gestor;
import com.upt.pt.service.GestorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gestores")
public class GestorController {

    private final GestorService gestorService;

    public GestorController(GestorService gestorService) {
        this.gestorService = gestorService;
    }
 
    @GetMapping
    public List<Gestor> getAll() {
        return gestorService.getAll();
    }

    @GetMapping("/{id}")
    public Gestor getById(@PathVariable Long id) {
        return gestorService.getById(id);
    }

    @PostMapping
    public Gestor create(@RequestBody Gestor g) {
        return gestorService.create(g);
    }

    @PutMapping("/{id}")
    public Gestor update(@PathVariable Long id, @RequestBody Gestor g) {
        return gestorService.update(id, g);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        gestorService.delete(id);
    }
}

