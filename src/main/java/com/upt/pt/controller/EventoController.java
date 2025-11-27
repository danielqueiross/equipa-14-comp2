package com.upt.pt.controller;

import com.upt.pt.entity.Evento;
import com.upt.pt.service.EventoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public List<Evento> getAll() {
        return eventoService.getAll();
    }

    @GetMapping("/{id}")
    public Evento getById(@PathVariable Long id) {
        return eventoService.getById(id);
    }

    @PostMapping
    public Evento create(@RequestBody Evento e) {
        return eventoService.create(e);
    }

    @PutMapping("/{id}")
    public Evento update(@PathVariable Long id, @RequestBody Evento e) {
        return eventoService.update(id, e);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        eventoService.delete(id);
    }
}
