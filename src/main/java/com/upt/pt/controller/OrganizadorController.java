package com.upt.pt.controller;

import com.upt.pt.dto.OrganizadorDTO;
import com.upt.pt.entity.Organizador;
import com.upt.pt.mapper.OrganizadorMapper;
import com.upt.pt.service.OrganizadorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/organizadores")
public class OrganizadorController {

    private final OrganizadorService organizadorService;

    public OrganizadorController(OrganizadorService organizadorService) {
        this.organizadorService = organizadorService;
    }

    @GetMapping
    public List<OrganizadorDTO> getAll() {
        List<Organizador> list = organizadorService.getAll();
        return list.stream()
                .map(OrganizadorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OrganizadorDTO getById(@PathVariable Long id) {
        Organizador o = organizadorService.getById(id);
        return OrganizadorMapper.toDTO(o);
    }

    @PostMapping
    public OrganizadorDTO create(@RequestBody OrganizadorDTO dto) {
        Organizador entity = OrganizadorMapper.toEntity(dto);
        Organizador saved = organizadorService.create(entity);
        return OrganizadorMapper.toDTO(saved);
    }

    @PutMapping("/{id}")
    public OrganizadorDTO update(@PathVariable Long id, @RequestBody OrganizadorDTO dto) {
        Organizador entity = OrganizadorMapper.toEntity(dto);
        Organizador updated = organizadorService.update(id, entity);
        return OrganizadorMapper.toDTO(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        organizadorService.delete(id);
    }
}
