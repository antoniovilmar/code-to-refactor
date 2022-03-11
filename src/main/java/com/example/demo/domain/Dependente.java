package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Dependente {
    @Id
    private String cpf;
    private String telefone;

    public Dependente(String cpf, String telefone) {
        this.cpf = cpf;
        this.telefone = telefone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dependente that = (Dependente) o;
        return Objects.equals(cpf, that.cpf) && Objects.equals(telefone, that.telefone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf, telefone);
    }
}
