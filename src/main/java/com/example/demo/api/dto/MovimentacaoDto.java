package com.example.demo.api.dto;


public class MovimentacaoDto {
    private double valor;
    public MovimentacaoDto(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }
}
