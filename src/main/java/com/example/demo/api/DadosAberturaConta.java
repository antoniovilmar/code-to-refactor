package com.example.demo.api;

public class DadosAberturaConta {
    private long agencia;
    private String cpfTitular;

    public DadosAberturaConta(long agencia, String cpfTitular) {
        this.agencia = agencia;
        this.cpfTitular = cpfTitular;
    }

    public long getAgencia() {
        return agencia;
    }

    public String getCpfTitular() {
        return cpfTitular;
    }
}
