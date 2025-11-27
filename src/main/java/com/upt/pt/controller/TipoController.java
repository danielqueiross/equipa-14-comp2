package com.upt.pt.controller;

import com.upt.pt.entity.Tipo;
import com.upt.pt.service.TipoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos")
public class TipoController {

    private final TipoService tipoService;

    public TipoController(TipoService tipoService) {
        this.tipoService = tipoService;
    }

    @GetMapping
    public List<Tipo> getAll() {
        return tipoService.getAll();
    }

    @GetMapping("/{id}")
    public Tipo getById(@PathVariable Long id) {
        return tipoService.getById(id);
    }

    @PostMapping
    public Tipo create(@RequestBody Tipo t) {
        return tipoService.create(t);
    }

    @PutMapping("/{id}")
    public Tipo update(@PathVariable Long id, @RequestBody Tipo t) {
        return tipoService.update(id, t);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tipoService.delete(id);
    }
}