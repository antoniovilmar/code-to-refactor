package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Dependente {
    @Id
    private String cpf;
    private String telefone;

    public Dependente() {
    }

    public Dependente(String cpf, String telefone) {
        this.cpf = cpf;
        this.telefone = telefone;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Dependente) {
            Dependente that = (Dependente) o;
            return Objects.equals(cpf, that.cpf) && Objects.equals(telefone, that.telefone);
        }else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf, telefone);
    }
}
