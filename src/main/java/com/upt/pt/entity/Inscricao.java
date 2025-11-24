package com.upt.pt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Inscricao")
public class Inscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nomeParticipante;
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "id_estudante")
    private Estudante estudante;

    public Inscricao() {}

    public Inscricao(String nomeParticipante, String email) {
        this.nomeParticipante = nomeParticipante;
        this.email = email;
    }

    public int getId() { return id; }
    public String getNomeParticipante() { return nomeParticipante; }
    public void setNomeParticipante(String nomeParticipante) { this.nomeParticipante = nomeParticipante; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Evento getEvento() { return evento; }
    public void setEvento(Evento evento) { this.evento = evento; }

    public Estudante getEstudante() { return estudante; }
    public void setEstudante(Estudante estudante) { this.estudante = estudante; }

    @Override
    public String toString() {
        return "Inscrição{" +
                "id=" + id +
                ", nomeParticipante='" + nomeParticipante + '\'' +
                ", email='" + email + '\'' +
                ", evento=" + (evento != null ? evento.getTitulo() : "nenhum") +
                '}';
    }
}
