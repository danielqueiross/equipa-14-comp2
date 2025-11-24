package com.upt.pt.service;

import com.upt.pt.entity.Gestor;
import com.upt.pt.repository.GestorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GestorService {

    private final GestorRepository gestorRepository;

    public GestorService(GestorRepository gestorRepository) {
        this.gestorRepository = gestorRepository;
    }

    public List<Gestor> getAll() {
        return gestorRepository.findAll();
    }

    public Gestor getById(Long id) {
        return gestorRepository.findById(id).orElse(null);
    }

    public Gestor create(Gestor g) {
        return gestorRepository.save(g);
    }

    public Gestor update(Long id, Gestor dados) {
        Optional<Gestor> opt = gestorRepository.findById(id);
        if (opt.isEmpty()) return null;

        Gestor g = opt.get();

        if (dados.getNome() != null) g.setNome(dados.getNome());
        if (dados.getEmail() != null) g.setEmail(dados.getEmail());
        if (dados.getPassword() != null) g.setPassword(dados.getPassword());

        return gestorRepository.save(g);
    }

    public boolean delete(Long id) {
        if (!gestorRepository.existsById(id)) return false;
        gestorRepository.deleteById(id);
        return true;
    }
}
