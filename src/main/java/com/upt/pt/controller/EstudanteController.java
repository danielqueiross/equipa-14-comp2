package com.upt.pt.controller;

import com.upt.pt.entity.Estudante;
import com.upt.pt.service.EstudanteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudantes")
public class EstudanteController {

    private final EstudanteService estudanteService;

    public EstudanteController(EstudanteService estudanteService) {
        this.estudanteService = estudanteService;
    }

    @GetMapping
    public List<Estudante> getAll() {
        return estudanteService.getAll();
    }

    @GetMapping("/{id}")
    public Estudante getById(@PathVariable Long id) {
        return estudanteService.getById(id);
    }

    @PostMapping
    public Estudante create(@RequestBody Estudante e) {
        return estudanteService.create(e);
    }

    @PutMapping("/{id}")
    public Estudante update(@PathVariable Long id, @RequestBody Estudante e) {
        return estudanteService.update(id, e);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        estudanteService.delete(id);
    }
}
