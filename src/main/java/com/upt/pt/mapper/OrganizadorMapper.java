package com.upt.pt.mapper;

import com.upt.pt.dto.OrganizadorDTO;
import com.upt.pt.entity.Organizador;

public class OrganizadorMapper {

    public static OrganizadorDTO toDTO(Organizador o) {
        if (o == null) return null;

        return new OrganizadorDTO(
                (long) o.getId(),
                o.getNome(),
                o.getEmail()
        );
    }

    public static Organizador toEntity(OrganizadorDTO dto) {
        if (dto == null) return null;

        Organizador o = new Organizador();
        o.setNome(dto.getNome());
        o.setEmail(dto.getEmail());
        return o;
    }
}