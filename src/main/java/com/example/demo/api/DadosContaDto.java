package com.example.demo.api;

public class DadosContaDto {
    private long agencia;
    private String cpfTitular;

    private String dependente;

    public DadosContaDto(long agencia, String cpfTitular, String dependente) {
        this.agencia = agencia;
        this.cpfTitular = cpfTitular;
        this.dependente = dependente;
    }

    public long getAgencia() {
        return agencia;
    }

    public String getCpfTitular() {
        return cpfTitular;
    }

    public String getDependente() {
        return dependente;
    }
}
