package com.example.demo.domain.movimentacao.evento;

import com.example.demo.domain.movimentacao.TipoMovimentacao;

public abstract class MovimentoRealizado {
    private long numeroConta;
    private long agencia;
    private double valorMovimentado;
    private double saldoAposMovimento;

    public MovimentoRealizado(long numeroConta, long agencia, double valorMovimentado, double saldoAposMovimento) {
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.valorMovimentado = valorMovimentado;
        this.saldoAposMovimento = saldoAposMovimento;
    }

    public abstract TipoMovimentacao getTipoMovimentacao();

    public long getNumeroConta() {
        return numeroConta;
    }

    public long getAgencia() {
        return agencia;
    }

    public double getValorMovimentado() {
        return valorMovimentado;
    }

    public double getSaldoAposMovimento() {
        return saldoAposMovimento;
    }
}
