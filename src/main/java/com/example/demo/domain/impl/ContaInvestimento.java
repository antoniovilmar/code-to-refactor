package com.example.demo.domain.impl;

import com.example.demo.domain.Conta;
import com.example.demo.domain.Dependente;
import com.example.demo.domain.exception.DomainBusinessException;
import com.example.demo.domain.util.AccountUtil;
import java.util.Objects;

public class ContaInvestimento extends Conta {

    public ContaInvestimento(long number, long agency, String holderCPF) {
        super(number, agency, holderCPF);
    }

    public void deposit(final double valor) {
        setBalance(getBalance() + valor);
    }

    public void withdraw(double value) {
        throw new DomainBusinessException("Conta de Investimento não pode sacar, somente transferir");
    }

    public void includeDependent(final Dependente dependente) {
        throw new DomainBusinessException("Conta de Investimento não pode ter dependentes");
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ContaCorrente) {
            ContaInvestimento accountToCompare = (ContaInvestimento) o;
            ContaInvestimento thisAccount =
                    new ContaInvestimento(getNumber(), getAgency(), getHolderCPF());

            return AccountUtil.isTheSameFields(accountToCompare, thisAccount);
        }
        return false;

    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getAgency(), getHolderCPF());
    }

}