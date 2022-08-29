package com.example.demo.domain.impl;

import com.example.demo.domain.Account;
import com.example.demo.domain.Dependent;
import com.example.demo.domain.exception.DomainBusinessException;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class CheckingAccount implements Account {
    @Id
    private long number;
    private long agency;
    private String holderCPF;
    private double balance;
    @OneToMany
    private Set<Dependent> dependents;
    @Transient
    private static final double SPECIAL_CHECK_LIMIT = -1000;

    private CheckingAccount() {
        this.dependents = new HashSet<>();
    }

    public CheckingAccount(final long accountNumber, final long agency, final String holderCPF) {
        this();
        this.number = accountNumber;
        this.agency = agency;
        this.holderCPF = holderCPF;
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

    public void withdraw(double valor) {
        double newBalance = balance - valor;
        if (newBalance < SPECIAL_CHECK_LIMIT) {
            throw new DomainBusinessException("Saldo insuficiente");
        }

        this.balance = newBalance;
    }

    public void includeDependent(final Dependent dependent) {
        this.dependents.add(dependent);
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof CheckingAccount) {
            CheckingAccount account = (CheckingAccount) o;
            return isTheSameLongFields(account)
                    && isTheSameStringFields(account);
        }else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, agency, holderCPF);
    }

    private boolean isTheSameLongFields(CheckingAccount account){
        return number == account.number
                && agency == account.agency;
    }

    private boolean isTheSameStringFields(CheckingAccount account){
        return Objects.equals(holderCPF, account.holderCPF);
    }
}
