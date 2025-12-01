package com.upt.pt.controller;

import com.upt.pt.dto.EventoDTO;
import com.upt.pt.entity.Evento;
import com.upt.pt.mapper.EventoMapper;
import com.upt.pt.service.EventoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public List<EventoDTO> getAll() {
        List<Evento> list = eventoService.getAll();
        return list.stream()
                .map(EventoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EventoDTO getById(@PathVariable Long id) {
        Evento e = eventoService.getById(id);
        return EventoMapper.toDTO(e);
    }

    @PostMapping
    public EventoDTO create(@RequestBody EventoDTO dto) {
        Evento entity = EventoMapper.toEntity(dto);
        Evento saved = eventoService.create(entity);
        return EventoMapper.toDTO(saved);
    }

    @PutMapping("/{id}")
    public EventoDTO update(@PathVariable Long id, @RequestBody EventoDTO dto) {
        Evento entity = EventoMapper.toEntity(dto);
        Evento updated = eventoService.update(id, entity);
        return EventoMapper.toDTO(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        eventoService.delete(id);
    }
}
