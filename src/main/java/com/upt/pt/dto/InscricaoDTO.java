package com.upt.pt.dto;

public class InscricaoDTO {
    private Long id;
    private String nomeParticipante;
    private String email;

    private Long estudanteId;
    private Long eventoId;

    public InscricaoDTO() {}

    public InscricaoDTO(Long id, String nomeParticipante, String email,
                        Long estudanteId, Long eventoId) {
        this.id = id;
        this.nomeParticipante = nomeParticipante;
        this.email = email;
        this.estudanteId = estudanteId;
        this.eventoId = eventoId;
    }

    // getters e setters
}
