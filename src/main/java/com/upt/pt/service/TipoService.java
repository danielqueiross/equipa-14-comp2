package com.upt.pt.service;

import com.upt.pt.entity.Tipo;
import com.upt.pt.repository.TipoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoService {

    private final TipoRepository tipoRepository;

    public TipoService(TipoRepository tipoRepository) {
        this.tipoRepository = tipoRepository;
    }

    public List<Tipo> getAll() {
        return tipoRepository.findAll();
    }

    public Tipo getById(Long id) {
        return tipoRepository.findById(id).orElse(null);
    }

    public Tipo create(Tipo t) {
        return tipoRepository.save(t);
    }

    public Tipo update(Long id, Tipo dados) {
        return tipoRepository.findById(id)
                .map(tipo -> {
                    if (dados.getNome() != null) tipo.setNome(dados.getNome());
                    return tipoRepository.save(tipo);
                })
                .orElse(null);
    }

    public boolean delete(Long id) {
        if (!tipoRepository.existsById(id)) return false;
        tipoRepository.deleteById(id);
        return true;
    }
}
