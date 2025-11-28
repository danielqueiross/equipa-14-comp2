package com.upt.pt.mapper;

import com.upt.pt.dto.EventoDTO;
import com.upt.pt.entity.Evento;

import java.util.stream.Collectors;

public class EventoMapper {

    public static EventoDTO toDTO(Evento e) {
        if (e == null) return null;

        return new EventoDTO(
                (long) e.getId(),
                e.getTitulo(),
                e.getDescrição(),
                e.getData(),
                e.getLotaçãoMax(),
                e.getNumParticipantes(),
                e.getEstado(),
                e.getOrganizador() != null ? (long) e.getOrganizador().getId() : null,
                e.getTipo() != null ? (long) e.getTipo().getId() : null,
                e.getInscricoes()
                    .stream()
                    .map(ins -> (long) ins.getId())
                    .collect(Collectors.toList())
        );
    }

 
    public static Evento toEntity(EventoDTO dto) {
        if (dto == null) return null;

        Evento e = new Evento();

        e.setTitulo(dto.getTitulo());
        e.setDescrição(dto.getDescricao());
        e.setData(dto.getData());
        e.setLotaçãoMax(dto.getLotacaoMax());
        e.setNumParticipantes(dto.getNumParticipantes());
        e.setEstado(dto.getEstado());


        return e;
    }
}
