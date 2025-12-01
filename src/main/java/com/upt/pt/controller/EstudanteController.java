package com.upt.pt.controller;

import com.upt.pt.dto.EstudanteDTO;
import com.upt.pt.entity.Estudante;
import com.upt.pt.mapper.EstudanteMapper;
import com.upt.pt.service.EstudanteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/estudantes")
public class EstudanteController {

    private final EstudanteService estudanteService;

    public EstudanteController(EstudanteService estudanteService) {
        this.estudanteService = estudanteService;
    }

    @GetMapping
    public List<EstudanteDTO> getAll() {
        List<Estudante> list = estudanteService.getAll();
        return list.stream()
                .map(EstudanteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EstudanteDTO getById(@PathVariable Long id) {
        Estudante e = estudanteService.getById(id);
        return EstudanteMapper.toDTO(e);
    }

    @PostMapping
    public EstudanteDTO create(@RequestBody EstudanteDTO dto) {
        Estudante entity = EstudanteMapper.toEntity(dto);
        Estudante saved = estudanteService.create(entity);
        return EstudanteMapper.toDTO(saved);
    }

    @PutMapping("/{id}")
    public EstudanteDTO update(@PathVariable Long id, @RequestBody EstudanteDTO dto) {
        Estudante entity = EstudanteMapper.toEntity(dto);
        Estudante updated = estudanteService.update(id, entity);
        return EstudanteMapper.toDTO(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        estudanteService.delete(id);
    }
}
