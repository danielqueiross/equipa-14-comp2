package com.upt.pt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Organizador")
public class Organizador extends Utilizador {

    @OneToMany(mappedBy = "organizador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evento> eventos = new ArrayList<>();

    public Organizador() {}

    public List<Evento> getEventos() { return eventos; }
    public void setEventos(List<Evento> eventos) { this.eventos = eventos; }

    public void adicionarEvento(Evento e) {
        eventos.add(e);
        e.setOrganizador(this);
    }

    public void removerEvento(Evento e) {
        eventos.remove(e);
        e.setOrganizador(null);
    }

    @Override
    public String toString() {
        return "Organizador{" +
                "id=" + getId() +
                ", nome=" + getNome() +
                ", email=" + getEmail() +
                '}';
    }
}
