package com.upt.pt.dto;

import java.time.LocalDate;
import java.util.List;

public class EventoDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private LocalDate data;
    private int lotacaoMax;
    private int numParticipantes;
    private String estado;

    private Long organizadorId;
    private Long tipoId;
    private List<Long> inscricaoIds;

    public EventoDTO() {}

    public EventoDTO(Long id, String titulo, String descricao, LocalDate data,
                     int lotacaoMax, int numParticipantes, String estado,
                     Long organizadorId, Long tipoId, List<Long> inscricaoIds) {

        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.lotacaoMax = lotacaoMax;
        this.numParticipantes = numParticipantes;
        this.estado = estado;
        this.organizadorId = organizadorId;
        this.tipoId = tipoId;
        this.inscricaoIds = inscricaoIds;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public int getLotacaoMax() { return lotacaoMax; }
    public void setLotacaoMax(int lotacaoMax) { this.lotacaoMax = lotacaoMax; }

    public int getNumParticipantes() { return numParticipantes; }
    public void setNumParticipantes(int numParticipantes) { this.numParticipantes = numParticipantes; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Long getOrganizadorId() { return organizadorId; }
    public void setOrganizadorId(Long organizadorId) { this.organizadorId = organizadorId; }

    public Long getTipoId() { return tipoId; }
    public void setTipoId(Long tipoId) { this.tipoId = tipoId; }

    public List<Long> getInscricaoIds() { return inscricaoIds; }
    public void setInscricaoIds(List<Long> inscricaoIds) { this.inscricaoIds = inscricaoIds; }
}
