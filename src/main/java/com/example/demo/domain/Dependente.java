package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Dependente {
    @Id
    private String cpf;
    private String telefone;

    public Dependente(String cpf, String telefone) {
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }
}
