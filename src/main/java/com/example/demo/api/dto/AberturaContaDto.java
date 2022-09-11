package com.example.demo.api.dto;

public class AberturaContaDto {
    private long agencia;
    private String cpfTitular;

    public AberturaContaDto(long agencia, String cpfTitular) {
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
