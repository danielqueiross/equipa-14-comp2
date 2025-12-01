package com.upt.pt.mapper;

import com.upt.pt.dto.UtilizadorDTO;
import com.upt.pt.entity.Estudante;
import com.upt.pt.entity.Gestor;
import com.upt.pt.entity.Organizador;
import com.upt.pt.entity.Utilizador;

public class UtilizadorMapper {

    public static UtilizadorDTO toDTO(Utilizador u) {
        if (u == null) return null;

        String tipo = u instanceof Estudante ? "estudante" :
                      u instanceof Organizador ? "organizador" :
                      u instanceof Gestor ? "gestor" :
                      "desconhecido";

        return new UtilizadorDTO(
                (long) u.getId(),
                u.getNome(),
                u.getEmail(),
                u.getPassword(),
                tipo
        );
    }

    public static Utilizador toEntity(UtilizadorDTO dto) {
        if (dto == null) return null;

        Utilizador u;

        switch (dto.getTipo().toLowerCase()) {
            case "estudante" -> u = new Estudante();
            case "organizador" -> u = new Organizador();
            case "gestor" -> u = new Gestor();
            default -> throw new IllegalArgumentException("Tipo de utilizador inválido: " + dto.getTipo());
        }

        u.setNome(dto.getNome());
        u.setEmail(dto.getEmail());
        u.setPassword(dto.getPassword());
        return u;
    }
}
