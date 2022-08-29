package com.example.demo.domain.impl;

import com.example.demo.domain.Account;
import com.example.demo.domain.Dependent;
import com.example.demo.domain.exception.DomainBusinessException;

import javax.persistence.*;
import java.util.Objects;

public class InvestmentAccount implements Account {
    @Id
    private long number;
    private long agency;
    private double balance;
    private String holderCPF;

    private InvestmentAccount() { }

    public InvestmentAccount(final String holderCPF, final long accountNumber, final long agency) {
        this.holderCPF = holderCPF;
        this.number = accountNumber;
        this.agency = agency;
    }

    @Override
    public long getNumber() {
        return number;
    }

    @Override
    public long getAgency() {
        return agency;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getHolderCPF() {
        return holderCPF;
    }

    public void deposit(final double valor) {
        this.balance += valor;
    }

    public void withdraw(double value) {
        throw new DomainBusinessException("Conta de Investimento não pode sacar, somente transferir");
    }

    public void includeDependent(final Dependent dependent) {
        throw new DomainBusinessException("Conta de Investimento não pode ter dependentes");
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CheckingAccount) {
            InvestmentAccount account = (InvestmentAccount) o;
            return number == account.number
                    && agency == account.agency
                    && Objects.equals(holderCPF, account.holderCPF);
        }
        return false;

    }

    @Override
    public int hashCode() {
        return Objects.hash(number, agency, holderCPF);
    }
}
