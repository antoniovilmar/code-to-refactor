package com.example.demo.domain;

import javax.persistence.Id;

public abstract class Conta {
    @Id
    private long numero;
    private long agencia;
    private String cpfTitular;
    private double balance;
    private TipoConta tipoConta;

    protected Conta(long numero, long agencia, String cpfTitular, TipoConta tipoConta) {
        this.numero = numero;
        this.agencia = agencia;
        this.cpfTitular = cpfTitular;
        this.tipoConta = tipoConta;
    }

    public long getNumero() {
        return numero;
    }

    public long getAgencia() {
        return agencia;
    }

    public String getCpfTitular() {
        return cpfTitular;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }
}
