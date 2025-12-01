package com.upt.pt.controller;

import com.upt.pt.dto.UtilizadorDTO;
import com.upt.pt.entity.Utilizador;
import com.upt.pt.mapper.UtilizadorMapper;
import com.upt.pt.service.UtilizadorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/utilizadores")
public class UtilizadorController {

    private final UtilizadorService utilizadorService;

    public UtilizadorController(UtilizadorService utilizadorService) {
        this.utilizadorService = utilizadorService;
    }

    @GetMapping
    public List<UtilizadorDTO> getAll() {
        return utilizadorService.getAll()
                .stream()
                .map(UtilizadorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UtilizadorDTO getById(@PathVariable Long id) {
        Utilizador u = utilizadorService.getById(id);
        return UtilizadorMapper.toDTO(u);
    }

    @PostMapping
    public UtilizadorDTO create(@RequestBody UtilizadorDTO dto) {
        Utilizador entity = UtilizadorMapper.toEntity(dto);
        Utilizador saved = utilizadorService.create(entity);
        return UtilizadorMapper.toDTO(saved);
    }

    @PutMapping("/{id}")
    public UtilizadorDTO update(@PathVariable Long id, @RequestBody UtilizadorDTO dto) {
        Utilizador entity = UtilizadorMapper.toEntity(dto);
        Utilizador updated = utilizadorService.update(id, entity);
        return UtilizadorMapper.toDTO(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        utilizadorService.delete(id);
    }
}
