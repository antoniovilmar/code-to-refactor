package com.example.demo.domain.conta;

import com.example.demo.domain.TipoConta;
import com.example.demo.domain.exception.DominioException;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Conta extends AbstractAggregateRoot {
    @Id
    private long numero;
    private long agencia;
    private String cpfTitular;
    private double saldo;
    private TipoConta tipoConta;

    protected Conta(long numero, long agencia, String cpfTitular, TipoConta tipoConta) {
        this.numero = numero;
        this.agencia = agencia;
        this.cpfTitular = cpfTitular;
        this.tipoConta = tipoConta;
    }

    protected Conta() {
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

    public void depositar(double valor) {
        this.saldo += valor;
        this.registerEvent(new DepositoRealizadoEvent(this.numero, this.agencia, valor, this.saldo));
    }

    protected void sacar(double valor) {
        double novoSaldo = this.saldo - valor;
        this.validarSaque(novoSaldo);
        this.saldo -= valor;
        this.registerEvent(new SaqueRealizadoEvent(this.numero, this.agencia, valor, this.saldo));
    }

    public void validarSaque(double novoSaldo) {
        if (novoSaldo < this.getSaldoMinimoPermitido()) {
            throw new DominioException("Saldo insuficiente! Seu saldo não pode ser menor que: " + this.getSaldoMinimoPermitido());
        }
    }

    protected double getSaldoMinimoPermitido() {
        return 0;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }
}
