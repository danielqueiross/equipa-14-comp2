package com.upt.pt.service;

import com.upt.pt.entity.Organizador;
import com.upt.pt.repository.OrganizadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizadorService {

    private final OrganizadorRepository organizadorRepository;

    public OrganizadorService(OrganizadorRepository organizadorRepository) {
        this.organizadorRepository = organizadorRepository;
    }

    public List<Organizador> getAll() {
        return organizadorRepository.findAll();
    }

    public Organizador getById(Long id) {
        return organizadorRepository.findById(id).orElse(null);
    }

    public Organizador create(Organizador o) {
        return organizadorRepository.save(o);
    }

    public Organizador update(Long id, Organizador dados) {
        Optional<Organizador> opt = organizadorRepository.findById(id);
        if (opt.isEmpty()) return null;

        Organizador o = opt.get();

        if (dados.getNome() != null) o.setNome(dados.getNome());
        if (dados.getEmail() != null) o.setEmail(dados.getEmail());
        if (dados.getPassword() != null) o.setPassword(dados.getPassword());

        return organizadorRepository.save(o);
    }

    public boolean delete(Long id) {
        if (!organizadorRepository.existsById(id)) return false;
        organizadorRepository.deleteById(id);
        return true;
    }
}
