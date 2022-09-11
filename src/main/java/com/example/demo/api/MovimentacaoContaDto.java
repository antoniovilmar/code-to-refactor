package com.example.demo.api;

import com.example.demo.domain.TipoMovimentacao;

public class MovimentacaoContaDto {
    private double valor;
    private TipoMovimentacao movimento;

    public MovimentacaoContaDto(double valor, TipoMovimentacao movimento) {
        this.valor = valor;
        this.movimento = movimento;
    }

    public double getValor() {
        return valor;
    }

    public TipoMovimentacao getMovimento() {
        return movimento;
    }
}
