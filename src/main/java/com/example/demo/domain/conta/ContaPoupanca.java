package com.example.demo.domain.conta;

import com.example.demo.domain.TipoConta;

import javax.persistence.Entity;

@Entity
public class ContaPoupanca extends Conta {

    private ContaPoupanca() {
        super();
    }

    public ContaPoupanca(long numeroConta, long agencia, String cpf) {
        super(numeroConta, agencia, cpf, TipoConta.POUPANCA);
    }

}
