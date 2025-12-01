package com.upt.pt.controller;

import com.upt.pt.dto.TipoDTO;
import com.upt.pt.entity.Tipo;
import com.upt.pt.mapper.TipoMapper;
import com.upt.pt.service.TipoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tipos")
public class TipoController {

    private final TipoService tipoService;

    public TipoController(TipoService tipoService) {
        this.tipoService = tipoService;
    }

    @GetMapping
    public List<TipoDTO> getAll() {
        List<Tipo> list = tipoService.getAll();
        return list.stream()
                .map(TipoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TipoDTO getById(@PathVariable Long id) {
        Tipo t = tipoService.getById(id);
        return TipoMapper.toDTO(t);
    }

    @PostMapping
    public TipoDTO create(@RequestBody TipoDTO dto) {
        Tipo entity = TipoMapper.toEntity(dto);
        Tipo saved = tipoService.create(entity);
        return TipoMapper.toDTO(saved);
    }

    @PutMapping("/{id}")
    public TipoDTO update(@PathVariable Long id, @RequestBody TipoDTO dto) {
        Tipo entity = TipoMapper.toEntity(dto);
        Tipo updated = tipoService.update(id, entity);
        return TipoMapper.toDTO(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tipoService.delete(id);
    }
}
