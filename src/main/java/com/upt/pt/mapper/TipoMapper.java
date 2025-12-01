package com.upt.pt.mapper;

import com.upt.pt.dto.TipoDTO;
import com.upt.pt.entity.Tipo;

public class TipoMapper {


    public static TipoDTO toDTO(Tipo t) {
        if (t == null) return null;

        return new TipoDTO(
                (long) t.getId(),
                t.getNome()
        );
    }

    public static Tipo toEntity(TipoDTO dto) {
        if (dto == null) return null;

        Tipo t = new Tipo();
        t.setNome(dto.getNome());
        return t;
    }
}
