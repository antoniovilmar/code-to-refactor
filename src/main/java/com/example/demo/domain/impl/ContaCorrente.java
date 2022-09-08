package com.example.demo.domain.impl;

import com.example.demo.domain.Conta;
import com.example.demo.domain.Dependente;
import com.example.demo.domain.exception.DomainBusinessException;
import com.example.demo.domain.util.AccountUtil;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class ContaCorrente extends Conta {
    @OneToMany
    private Set<Dependente> dependentes;
    @Transient
    private static final double SPECIAL_CHECK_LIMIT = -1000;

    //get
    //set

    public ContaCorrente(long number, long agency, String holderCPF) {
        super(number, agency, holderCPF);
        dependentes = new HashSet<>();
    }

    public void deposit(final double valor) {
        setBalance(getBalance() + valor);
    }

    public void withdraw(double valor) {
        double newBalance = getBalance() - valor;
        if (newBalance < SPECIAL_CHECK_LIMIT) {
            throw new DomainBusinessException("Saldo insuficiente");
        }
        setBalance(newBalance);
    }

    public void includeDependent(final Dependente dependente) {
        this.dependentes.add(dependente);
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof ContaCorrente) {
            ContaCorrente accountToCompare = (ContaCorrente) o;
            ContaCorrente thisAccount =
                    new ContaCorrente(getNumber(), getAgency(), getHolderCPF());

            return AccountUtil.isTheSameFields(accountToCompare, thisAccount);
        }
            return false;
    }



    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getAgency(), getHolderCPF());
    }

}
