package com.upt.pt.service;

import com.upt.pt.entity.Inscricao;
import com.upt.pt.repository.InscricaoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InscricaoService {

    private final InscricaoRepository inscricaoRepository;

    public InscricaoService(InscricaoRepository inscricaoRepository) {
        this.inscricaoRepository = inscricaoRepository;
    }

    public List<Inscricao> getAll() {
        return inscricaoRepository.findAll();
    }

    public Inscricao getById(Long id) {
        return inscricaoRepository.findById(id).orElse(null);
    }

    public Inscricao create(Inscricao i) {
        return inscricaoRepository.save(i);
    }

    public Inscricao update(Long id, Inscricao dados) {
        return inscricaoRepository.findById(id)
                .map(inscricao -> {
                    if (dados.getNomeParticipante() != null)
                        inscricao.setNomeParticipante(dados.getNomeParticipante());

                    if (dados.getEmail() != null)
                        inscricao.setEmail(dados.getEmail());

                    return inscricaoRepository.save(inscricao);
                }).orElse(null);
    }

    public boolean delete(Long id) {
        if (!inscricaoRepository.existsById(id)) return false;
        inscricaoRepository.deleteById(id);
        return true;
    }
}
