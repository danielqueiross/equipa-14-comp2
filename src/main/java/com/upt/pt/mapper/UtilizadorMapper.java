package com.upt.pt.mapper;

import com.upt.pt.dto.UtilizadorDTO;
import com.upt.pt.entity.Utilizador;

public class UtilizadorMapper {

    public static UtilizadorDTO toDTO(Utilizador u) {
        if (u == null) return null;
        return new UtilizadorDTO(
                (long) u.getId(),
                u.getNome(),
                u.getEmail(),
                u.getPassword());
    }

    public static Utilizador toEntity(UtilizadorDTO dto) {
        if (dto == null) return null;

        Utilizador u = new Utilizador();
        u.setNome(dto.getNome());
        u.setEmail(dto.getEmail());
        u.setPassword(dto.getPassword());
        return u;
    }
}
