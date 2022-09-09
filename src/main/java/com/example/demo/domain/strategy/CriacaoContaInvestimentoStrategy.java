package com.example.demo.domain.strategy;

import com.example.demo.domain.Conta;
import com.example.demo.domain.TipoConta;
import com.example.demo.domain.impl.ContaInvestimento;

public class CriacaoContaInvestimentoStrategy implements CriacaoContaStrategy {
    @Override
    public Boolean ifAccountType(TipoConta tipoConta) {
        return tipoConta.equals(TipoConta.POUPANCA);
    }

    @Override
    public Conta create(long number, long agency, String holderCPF) {
        return new ContaInvestimento(number, agency, holderCPF);
    }
}
