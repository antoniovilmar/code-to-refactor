package com.example.demo.api;

public class DadosDependenteInclusao {
    private String cpf;
    private String telefone;

    public DadosDependenteInclusao(String cpf, String telefone) {
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
