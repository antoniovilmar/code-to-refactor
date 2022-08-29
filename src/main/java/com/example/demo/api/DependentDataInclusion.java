package com.example.demo.api;

public class DependentDataInclusion {
    private String cpf;
    private String telefone;

    public DependentDataInclusion(String cpf, String telefone) {
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
