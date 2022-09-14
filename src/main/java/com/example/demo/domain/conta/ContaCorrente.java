package com.example.demo.domain.conta;

import com.example.demo.domain.TipoConta;

import javax.persistence.Entity;

@Entity
public class ContaCorrente extends Conta {

    private ContaCorrente() {
        super();
    }

    public ContaCorrente(long number, long agency, String holderCPF) {
        super(number, agency, holderCPF, TipoConta.CORRENTE);
    }

    @Override
    protected double getSaldoMinimoPermitido() {
        return -1000;
    }

}
