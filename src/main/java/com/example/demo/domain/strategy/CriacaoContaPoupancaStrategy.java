package com.example.demo.domain.strategy;

import com.example.demo.domain.Conta;
import com.example.demo.domain.TipoConta;
import com.example.demo.domain.impl.ContaPoupanca;

public class CriacaoContaPoupancaStrategy implements CriacaoContaStrategy {
    @Override
    public Boolean ifAccountType(TipoConta tipoConta) {
        return tipoConta.equals(TipoConta.POUPANCA);
    }

    @Override
    public Conta create(long number, long agency, String holderCPF) {
        return new ContaPoupanca(number, agency, holderCPF);
    }
}
