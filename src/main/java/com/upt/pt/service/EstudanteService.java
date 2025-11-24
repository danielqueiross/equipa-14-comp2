package com.upt.pt.service;

import com.upt.pt.entity.Estudante;
import com.upt.pt.repository.EstudanteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudanteService {

    private final EstudanteRepository estudanteRepository;

    public EstudanteService(EstudanteRepository estudanteRepository) {
        this.estudanteRepository = estudanteRepository;
    }

    public List<Estudante> getAll() {
        return estudanteRepository.findAll();
    }

    public Estudante getById(Long id) {
        return estudanteRepository.findById(id).orElse(null);
    }

    public Estudante create(Estudante e) {
        return estudanteRepository.save(e);
    }

    public Estudante update(Long id, Estudante dados) {
        Optional<Estudante> opt = estudanteRepository.findById(id);
        if (opt.isEmpty()) return null;

        Estudante e = opt.get();

        if (dados.getNome() != null) e.setNome(dados.getNome());
        if (dados.getEmail() != null) e.setEmail(dados.getEmail());
        if (dados.getPassword() != null) e.setPassword(dados.getPassword());

        return estudanteRepository.save(e);
    }

    public boolean delete(Long id) {
        if (!estudanteRepository.existsById(id)) return false;
        estudanteRepository.deleteById(id);
        return true;
    }

}
