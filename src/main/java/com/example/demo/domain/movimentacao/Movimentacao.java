package com.example.demo.domain.movimentacao;

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
    private TipoMovimentacao movimentacao;
    private LocalDateTime data;

    private Movimentacao() {
    }

    public Movimentacao(long numeroConta, long agencia, double saldoAposMovimento, double valorMovimentado, TipoMovimentacao movimentacao) {
        this.movimentacao = movimentacao;
        this.id = UUID.randomUUID();
        this.data = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.saldoAposMovimento = saldoAposMovimento;
        this.valorMovimentado = valorMovimentado;
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

    @Override
    public String toString() {
        return "Movimentacao{" +
                "numeroConta=" + numeroConta +
                ", agencia=" + agencia +
                ", saldoAposMovimento=" + saldoAposMovimento +
                ", valorMovimentado=" + valorMovimentado +
                ", data=" + data +
                '}';
    }
}
