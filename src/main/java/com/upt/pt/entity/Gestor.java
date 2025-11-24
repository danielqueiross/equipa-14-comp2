package com.upt.pt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Gestor")
public class Gestor extends Utilizador {

    public Gestor() {}

    @Override
    public String toString() {
        return "Gestor{" +
                "id=" + getId() +
                ", nome=" + getNome() +
                ", email=" + getEmail() +
                '}';
    }
}
