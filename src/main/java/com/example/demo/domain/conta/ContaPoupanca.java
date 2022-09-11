package com.example.demo.domain.conta;

import com.example.demo.domain.TipoConta;

import javax.persistence.Entity;

@Entity
public class ContaPoupanca extends Conta {

    private ContaPoupanca() {
        super();
    }

    public ContaPoupanca(long number, long agency, String holderCPF) {
        super(number, agency, holderCPF, TipoConta.POUPANCA);
    }

}
