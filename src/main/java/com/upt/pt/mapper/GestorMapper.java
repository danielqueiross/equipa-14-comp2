package com.upt.pt.mapper;

import com.upt.pt.dto.GestorDTO;
import com.upt.pt.entity.Gestor;

public class GestorMapper {

    public static GestorDTO toDTO(Gestor g) {
        if (g == null) return null;

        return new GestorDTO(
                (long) g.getId(),
                g.getNome(),
                g.getEmail()
        );
    }

    public static Gestor toEntity(GestorDTO dto) {
        if (dto == null) return null;

        Gestor g = new Gestor();
        g.setNome(dto.getNome());
        g.setEmail(dto.getEmail());
        return g;
    }
}