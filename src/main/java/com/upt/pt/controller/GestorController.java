package com.upt.pt.controller;

import com.upt.pt.dto.GestorDTO;
import com.upt.pt.entity.Gestor;
import com.upt.pt.mapper.GestorMapper;
import com.upt.pt.service.GestorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/gestores")
public class GestorController {

    private final GestorService gestorService;

    public GestorController(GestorService gestorService) {
        this.gestorService = gestorService;
    }

    @GetMapping
    public List<GestorDTO> getAll() {
        List<Gestor> list = gestorService.getAll();
        return list.stream()
                .map(GestorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public GestorDTO getById(@PathVariable Long id) {
        Gestor g = gestorService.getById(id);
        return GestorMapper.toDTO(g);
    }

    @PostMapping
    public GestorDTO create(@RequestBody GestorDTO dto) {
        Gestor entity = GestorMapper.toEntity(dto);
        Gestor saved = gestorService.create(entity);
        return GestorMapper.toDTO(saved);
    }

    @PutMapping("/{id}")
    public GestorDTO update(@PathVariable Long id, @RequestBody GestorDTO dto) {
        Gestor entity = GestorMapper.toEntity(dto);
        Gestor updated = gestorService.update(id, entity);
        return GestorMapper.toDTO(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        gestorService.delete(id);
    }
}
