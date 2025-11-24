package com.upt.pt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String titulo;
    private String descrição;
    private LocalDate data;
    private int lotaçãoMax;
    private int numParticipantes;
    private String estado;
    private String motivoRejeição;

    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private Tipo tipo;

    @ManyToOne
    @JoinColumn(name = "id_organizador")
    private Organizador organizador;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inscricao> inscricoes = new ArrayList<>();

    public Evento() {}

    public Evento(String titulo, String descrição, LocalDate data, int lotaçãoMax) {
        this.titulo = titulo;
        this.descrição = descrição;
        this.data = data;
        this.lotaçãoMax = lotaçãoMax;
        this.estado = "Pendente";
        this.numParticipantes = 0;
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public int getLotaçãoMax() { return lotaçãoMax; }
    public void setLotaçãoMax(int lotaçãoMax) { this.lotaçãoMax = lotaçãoMax; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public int getId() { return Id; }
    public String getDescrição() { return descrição; }
    public LocalDate getData() { return data; }
    public int getNumParticipantes() { return numParticipantes; }
    public String getMotivoRejeição() { return motivoRejeição; }

    public Organizador getOrganizador() { return organizador; }
    public void setOrganizador(Organizador organizador) { this.organizador = organizador; }

    public void setDescrição(String descrição) { this.descrição = descrição; }
    public void setData(LocalDate data) { this.data = data; }
    public void setNumParticipantes(int numParticipantes) { this.numParticipantes = numParticipantes; }
    public void setMotivoRejeição(String motivoRejeição) { this.motivoRejeição = motivoRejeição; }

    public Tipo getTipo() { return tipo; }
    public void setTipo(Tipo tipo) { this.tipo = tipo; }

    public List<Inscricao> getInscricoes() { return inscricoes; }

    public void adicionarInscricao(Inscricao i) {
        inscricoes.add(i);
        i.setEvento(this);
        numParticipantes++;
    }

    public void removerInscricao(Inscricao i) {
        inscricoes.remove(i);
        i.setEvento(null);
        numParticipantes--;
    }

    @Override
    public String toString() {
        return "Evento [Id=" + Id +
               ", titulo=" + titulo +
               ", descrição=" + descrição +
               ", data=" + data +
               ", lotaçãoMax=" + lotaçãoMax +
               ", numParticipantes=" + numParticipantes +
               ", estado=" + estado +
               ", motivoRejeição=" + motivoRejeição +
               "]";
    }
}
