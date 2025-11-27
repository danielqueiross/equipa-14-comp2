package com.upt.pt.controller;

import com.upt.pt.entity.Utilizador;
import com.upt.pt.service.UtilizadorService;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilizadores")
public class UtilizadorController {

    private final UtilizadorService utilizadorService;

    public UtilizadorController(UtilizadorService utilizadorService) {
        this.utilizadorService = utilizadorService;
    }

    @GetMapping
    public List<Utilizador> getAll() {
        return utilizadorService.getAll();
    }

    @GetMapping("/{id}")
    public Utilizador getById(@PathVariable Long id) {
        return utilizadorService.getById(id);
    }

    @PostMapping
    public Utilizador create(@RequestBody Utilizador u) {
        return utilizadorService.create(u);
    }

    @PutMapping("/{id}")
    public Utilizador update(@PathVariable Long id, @RequestBody Utilizador u) {
        return utilizadorService.update(id, u);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        utilizadorService.delete(id);
    }
}