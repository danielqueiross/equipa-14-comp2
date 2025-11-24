package com.upt.pt.service;

import com.upt.pt.entity.Evento;
import com.upt.pt.repository.EventoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public List<Evento> getAll() {
        return eventoRepository.findAll();
    }

    public Evento getById(Long id) {
        return eventoRepository.findById(id).orElse(null);
    }

    public Evento create(Evento e) {
        return eventoRepository.save(e);
    }

    public Evento update(Long id, Evento dados) {
        Optional<Evento> opt = eventoRepository.findById(id);
        if (opt.isEmpty()) return null;

        Evento e = opt.get();

        if (dados.getTitulo() != null) e.setTitulo(dados.getTitulo());
        if (dados.getDescrição() != null) e.setDescrição(dados.getDescrição());
        if (dados.getData() != null) e.setData(dados.getData());
        if (dados.getLotaçãoMax() != 0) e.setLotaçãoMax(dados.getLotaçãoMax());

        return eventoRepository.save(e);
    }

    public boolean delete(Long id) {
        if (!eventoRepository.existsById(id)) return false;
        eventoRepository.deleteById(id);
        return true;
    }
}
