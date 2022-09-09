package com.example.demo.api.dto;


public class TransacaoContaDto {
    private final long numeroConta;
    private final double valor;

    public TransacaoContaDto(long numeroConta, double valor) {
        this.numeroConta = numeroConta;
        this.valor = valor;
    }

    public long getNumeroConta() {
        return numeroConta;
    }

    public double getValor() {
        return valor;
    }
}
