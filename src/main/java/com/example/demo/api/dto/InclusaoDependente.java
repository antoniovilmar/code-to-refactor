package com.example.demo.api.dto;

public class InclusaoDependente {
    private String cpf;
    private String telefone;

    public InclusaoDependente(String cpf, String telefone) {
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }
}
