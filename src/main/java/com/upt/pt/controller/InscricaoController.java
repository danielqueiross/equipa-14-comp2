package com.upt.pt.controller;

import com.upt.pt.entity.Inscricao;
import com.upt.pt.service.InscricaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscricoes")
public class InscricaoController {

    private final InscricaoService inscricaoService;

    public InscricaoController(InscricaoService inscricaoService) {
        this.inscricaoService = inscricaoService;
    }

    @GetMapping
    public List<Inscricao> getAll() {
        return inscricaoService.getAll();
    }

    @GetMapping("/{id}")
    public Inscricao getById(@PathVariable Long id) {
        return inscricaoService.getById(id);
    }

    @PostMapping
    public Inscricao create(@RequestBody Inscricao i) {
        return inscricaoService.create(i);
    }

    @PutMapping("/{id}")
    public Inscricao update(@PathVariable Long id, @RequestBody Inscricao i) {
        return inscricaoService.update(id, i);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        inscricaoService.delete(id);
    }
}
