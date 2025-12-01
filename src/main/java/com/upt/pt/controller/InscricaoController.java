package com.upt.pt.controller;

import com.upt.pt.dto.InscricaoDTO;
import com.upt.pt.entity.Inscricao;
import com.upt.pt.mapper.InscricaoMapper;
import com.upt.pt.service.InscricaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/inscricoes")
public class InscricaoController {

    private final InscricaoService inscricaoService;

    public InscricaoController(InscricaoService inscricaoService) {
        this.inscricaoService = inscricaoService;
    }

    @GetMapping
    public List<InscricaoDTO> getAll() {
        List<Inscricao> list = inscricaoService.getAll();
        return list.stream()
                .map(InscricaoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public InscricaoDTO getById(@PathVariable Long id) {
        Inscricao i = inscricaoService.getById(id);
        return InscricaoMapper.toDTO(i);
    }

    @PostMapping
    public InscricaoDTO create(@RequestBody InscricaoDTO dto) {
        Inscricao entity = InscricaoMapper.toEntity(dto);
        Inscricao saved = inscricaoService.create(entity);
        return InscricaoMapper.toDTO(saved);
    }

    @PutMapping("/{id}")
    public InscricaoDTO update(@PathVariable Long id, @RequestBody InscricaoDTO dto) {
        Inscricao entity = InscricaoMapper.toEntity(dto);
        Inscricao updated = inscricaoService.update(id, entity);
        return InscricaoMapper.toDTO(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        inscricaoService.delete(id);
    }
}
