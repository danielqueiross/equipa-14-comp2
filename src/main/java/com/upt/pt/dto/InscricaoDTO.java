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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeParticipante() { return nomeParticipante; }
    public void setNomeParticipante(String nomeParticipante) { this.nomeParticipante = nomeParticipante; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Long getEstudanteId() { return estudanteId; }
    public void setEstudanteId(Long estudanteId) { this.estudanteId = estudanteId; }

    public Long getEventoId() { return eventoId; }
    public void setEventoId(Long eventoId) { this.eventoId = eventoId; }
}