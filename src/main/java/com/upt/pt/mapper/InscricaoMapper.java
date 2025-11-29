package com.upt.pt.mapper;

import com.upt.pt.dto.InscricaoDTO;
import com.upt.pt.entity.Inscricao;

public class InscricaoMapper {

    public static InscricaoDTO toDTO(Inscricao i) {
        if (i == null) return null;

        return new InscricaoDTO(
                (long) i.getId(),
                i.getNomeParticipante(),
                i.getEmail(),
                i.getEstudante() != null ? (long) i.getEstudante().getId() : null,
                i.getEvento() != null ? (long) i.getEvento().getId() : null
        );
    }

    public static Inscricao toEntity(InscricaoDTO dto) {
        if (dto == null) return null;

        Inscricao i = new Inscricao();
        i.setNomeParticipante(dto.getNomeParticipante());
        i.setEmail(dto.getEmail());
        // estudanteId e eventoId tratados no SERVICE
        return i;
    }
}