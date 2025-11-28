package com.upt.pt.mapper;

import com.upt.pt.dto.EstudanteDTO;
import com.upt.pt.entity.Estudante;

public class EstudanteMapper {

    public static EstudanteDTO toDTO(Estudante e) {
        if (e == null) return null;
        return new EstudanteDTO(
                (long) e.getId(),
                e.getNome(),
                e.getEmail()
        );
    }

    public static Estudante toEntity(EstudanteDTO dto) {
        if (dto == null) return null;

        Estudante e = new Estudante();
        e.setNome(dto.getNome());
        e.setEmail(dto.getEmail());
        return e;
    }
}