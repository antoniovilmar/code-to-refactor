package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
public class Movimentacao {
    @Id
    private UUID id;
    private long numeroConta;
    private long agencia;
    private double saldoAposMovimento;
    private double valorMovimentado;
    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;
    @Enumerated(EnumType.STRING)
    private TipoMovimentacao movimentacao;

    private LocalDateTime data;

    private Movimentacao() {
    }

    public Movimentacao(long numeroConta, long agencia, double saldoAposMovimento, double valorMovimentado, TipoConta tipoConta, TipoMovimentacao movimentacao) {
        this.id = UUID.randomUUID();
        this.data = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.saldoAposMovimento = saldoAposMovimento;
        this.valorMovimentado = valorMovimentado;
        this.tipoConta = tipoConta;
        this.movimentacao = movimentacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movimentacao)) return false;

        Movimentacao that = (Movimentacao) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public UUID getId() {
        return id;
    }

    public long getNumeroConta() {
        return numeroConta;
    }

    public long getAgencia() {
        return agencia;
    }

    public double getSaldoAposMovimento() {
        return saldoAposMovimento;
    }

    public double getValorMovimentado() {
        return valorMovimentado;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public TipoMovimentacao getMovimentacao() {
        return movimentacao;
    }

    public LocalDateTime getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Movimentacao{" +
                "numeroConta=" + numeroConta +
                ", agencia=" + agencia +
                ", saldoAposMovimento=" + saldoAposMovimento +
                ", valorMovimentado=" + valorMovimentado +
                ", tipoConta=" + tipoConta +
                ", movimentacao=" + movimentacao +
                ", data=" + data +
                '}';
    }
}
