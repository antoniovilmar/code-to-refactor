package com.example.demo.domain.impl;

import com.example.demo.domain.Conta;
import com.example.demo.domain.Dependente;
import com.example.demo.domain.exception.DomainBusinessException;
import com.example.demo.domain.util.AccountUtil;

import javax.persistence.*;
import java.util.Objects;


public class ContaPoupanca extends Conta {
    @Transient
    private static final double ZERO = 0;

    public ContaPoupanca(long number, long agency, String holderCPF) {
        super(number, agency, holderCPF);
    }

    //igual pra todos. -> colocar em outro local evitar dup
    public void deposit(final double valor) {
        setBalance(getBalance() + valor);
    }

    public void withdraw(double valor) {
        double newBalance = getBalance() - valor;
        if (newBalance < ZERO) {
            throw new DomainBusinessException("Saldo insuficiente");
        }
        setBalance(newBalance);
    }

    public void includeDependent(final Dependente dependente) {
        throw new DomainBusinessException("Conta de Poupança não pode ter dependentes");
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ContaPoupanca) {
            ContaPoupanca accountToCompare = (ContaPoupanca) o;
            ContaPoupanca thisAccount =
                    new ContaPoupanca(getNumber(), getAgency(), getHolderCPF());

            return AccountUtil.isTheSameFields(accountToCompare, thisAccount);
        }
        return false;

    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getAgency(), getHolderCPF());
    }

}
