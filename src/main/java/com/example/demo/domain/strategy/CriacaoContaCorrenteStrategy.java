package com.example.demo.domain.strategy;

import com.example.demo.domain.Conta;
import com.example.demo.domain.TipoConta;
import com.example.demo.domain.impl.ContaCorrente;

public class CriacaoContaCorrenteStrategy implements CriacaoContaStrategy {
    @Override
    public Boolean ifAccountType(TipoConta tipoConta) {
        return tipoConta.equals(TipoConta.CORRENTE);
    }

    @Override
    public Conta create(long number, long agency, String holderCPF) {
        return new ContaCorrente(number, agency, holderCPF);
    }
}
