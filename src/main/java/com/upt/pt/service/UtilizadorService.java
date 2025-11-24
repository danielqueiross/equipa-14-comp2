package com.upt.pt.service;

import com.upt.pt.entity.Utilizador;
import com.upt.pt.repository.UtilizadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilizadorService {

    private final UtilizadorRepository utilizadorRepository;

    public UtilizadorService(UtilizadorRepository utilizadorRepository) {
        this.utilizadorRepository = utilizadorRepository;
    }

    // ESTE MÉTODO É NECESSÁRIO PARA O CONTROLLER
    public List<Utilizador> getAll() {
        return utilizadorRepository.findAll();
    }

    public Utilizador getById(Long id) {
        return utilizadorRepository.findById(id).orElse(null);
    }

    public Utilizador create(Utilizador u) {
        return utilizadorRepository.save(u);
    }

    public Utilizador update(Long id, Utilizador dados) {

        Optional<Utilizador> opt = utilizadorRepository.findById(id);

        if (opt.isEmpty()) {
            return null;
        }

        Utilizador u = opt.get();

        if (dados.getNome() != null) u.setNome(dados.getNome());
        if (dados.getEmail() != null) u.setEmail(dados.getEmail());
        if (dados.getPassword() != null) u.setPassword(dados.getPassword());

        return utilizadorRepository.save(u);
    }

    public boolean delete(Long id) {
        if (!utilizadorRepository.existsById(id)) {
            return false;
        }
        utilizadorRepository.deleteById(id);
        return true;
    }
}
