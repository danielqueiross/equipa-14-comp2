package com.upt.pt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Estudante")
public class Estudante extends Utilizador {

    @OneToMany(mappedBy = "estudante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inscricao> inscrições = new ArrayList<>();

    public Estudante() {}

    public List<Inscricao> getInscrições() { return inscrições; }

    public void adicionarInscrição(Inscricao inscrição) {
        inscrições.add(inscrição);
        inscrição.setEstudante(this);
    }

    public void removerInscrição(Inscricao inscrição) {
        inscrições.remove(inscrição);
        inscrição.setEstudante(null);
    }

    @Override
    public String toString() {
        return "Estudante [id=" + getId() +
               ", nome=" + getNome() +
               ", email=" + getEmail() + "]";
    }
}
