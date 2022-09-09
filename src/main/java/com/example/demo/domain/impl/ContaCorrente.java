package com.example.demo.domain.impl;

import com.example.demo.domain.*;
import com.example.demo.domain.exception.DominioException;
import com.example.demo.domain.util.AccountUtil;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class ContaCorrente extends Conta implements Deposita, Saque, IncluiDependente {
    @OneToMany
    private Set<Dependente> dependentes;
    @Transient
    private static final double SPECIAL_CHECK_LIMIT = -1000;

    //get
    //set

    public ContaCorrente(long number, long agency, String holderCPF) {
        super(number, agency, holderCPF, TipoConta.CORRENTE);
        dependentes = new HashSet<>();
    }

    @Override
    public void depositar(final double valor) {
        setBalance(getBalance() + valor);
    }

    @Override
    public void sacar(double valor) {
        double newBalance = getBalance() - valor;
        if (newBalance < SPECIAL_CHECK_LIMIT) {
            throw new DominioException("Saldo insuficiente");
        }
        setBalance(newBalance);
    }

    @Override
    public void incluirDependente(final Dependente dependente) {
        this.dependentes.add(dependente);
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof ContaCorrente) {
            ContaCorrente accountToCompare = (ContaCorrente) o;
            ContaCorrente thisAccount =
                    new ContaCorrente(getNumero(), getAgencia(), getCpfTitular());

            return AccountUtil.isTheSameFields(accountToCompare, thisAccount);
        }
            return false;
    }



    @Override
    public int hashCode() {
        return Objects.hash(getNumero(), getAgencia(), getCpfTitular());
    }

}
