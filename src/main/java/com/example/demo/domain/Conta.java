package com.example.demo.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Conta {
    @Id
    private long numero;
    private long agencia;
    private String cpfTitular;
    @OneToMany
    private Set<Dependente> dependentes;
    private double saldo;

    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;

    @Transient
    private double LIMITE_CHEQUE_ESPECIAL = -1000;

    private Conta() {
        this.dependentes = new HashSet();
    }

    public Conta(final String cpfTitular, final TipoConta tipoConta, final long numeroConta, final long agencia) {
        this();
        this.cpfTitular = cpfTitular;
        this.tipoConta = tipoConta;
        this.numero = numeroConta;
        this.agencia = agencia;
    }

    public void depositar(final double valor) {
        this.saldo += valor;
    }

    public void sacar(double valor) {
        if (TipoConta.INVESTIMENTO.equals(this.tipoConta)) {
            throw new DomainBusinessException("Conta de Investimento não pode sacar, somente transferir");
        }

        double novoSaldo = saldo - valor;

        if (TipoConta.POUPANCA.equals(this.tipoConta)) {
            if (novoSaldo < 0) {
                throw new DomainBusinessException("Saldo insuficiente");
            }
        } else {
            if (novoSaldo < LIMITE_CHEQUE_ESPECIAL) {
                throw new DomainBusinessException("Saldo insuficiente");
            }
        }

        this.saldo = novoSaldo;
    }

    public void incluirDependente(final Dependente dependente) {
        if (TipoConta.INVESTIMENTO.equals(this.tipoConta) || TipoConta.POUPANCA.equals(this.tipoConta)) {
            throw new DomainBusinessException("Conta de Investimento/Poupança não pode ter dependentes");
        }
        this.dependentes.add(dependente);

    }

    public double getSaldo() {
        return saldo;
    }

    public long getNumero() {
        return numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return numero == conta.numero && agencia == conta.agencia && Objects.equals(cpfTitular, conta.cpfTitular) && tipoConta == conta.tipoConta;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, agencia, cpfTitular, tipoConta);
    }
}
