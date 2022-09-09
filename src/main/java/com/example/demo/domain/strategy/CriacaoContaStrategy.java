package com.example.demo.domain.strategy;

import com.example.demo.domain.Conta;
import com.example.demo.domain.TipoConta;

public interface CriacaoContaStrategy {
    Boolean ifAccountType(TipoConta tipoConta);
    Conta create(long number, long agency, String holderCPF);

    //concatenar com template method junto ver!!
}
