package com.example.demo.domain.conta;

import com.example.demo.domain.*;

import javax.persistence.Entity;
@Entity
public class ContaInvestimento extends Conta {

    private ContaInvestimento() {
        super();
    }
    public ContaInvestimento(long numeroConta, long agencia, String cpf) {
        super(numeroConta, agencia, cpf, TipoConta.INVESTIMENTO);
    }

}