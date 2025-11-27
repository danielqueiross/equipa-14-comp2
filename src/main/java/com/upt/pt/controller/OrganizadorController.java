package com.upt.pt.controller;

import com.upt.pt.entity.Organizador;
import com.upt.pt.service.OrganizadorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizadores")
public class OrganizadorController {

    private final OrganizadorService organizadorService;

    public OrganizadorController(OrganizadorService organizadorService) {
        this.organizadorService = organizadorService;
    }

    @GetMapping
    public List<Organizador> getAll() {
        return organizadorService.getAll();
    }

    @GetMapping("/{id}")
    public Organizador getById(@PathVariable Long id) {
        return organizadorService.getById(id);
    }

    @PostMapping
    public Organizador create(@RequestBody Organizador o) {
        return organizadorService.create(o);
    }

    @PutMapping("/{id}")
    public Organizador update(@PathVariable Long id, @RequestBody Organizador o) {
        return organizadorService.update(id, o);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        organizadorService.delete(id);
    }
}

