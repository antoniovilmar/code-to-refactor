package com.example.demo.domain.impl;

import com.example.demo.domain.Account;
import com.example.demo.domain.Dependent;
import com.example.demo.domain.exception.DomainBusinessException;

import javax.persistence.*;
import java.util.Objects;


public class SavingsAccount implements Account {
    @Id
    private long number;
    private long agency;
    private String holderCPF;
    private double balance;
    @Transient
    private static final double ZERO = 0;

    private SavingsAccount() { }

    public SavingsAccount(final String holderCPF, final long accountNumber, final long agency) {
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

    public void withdraw(double valor) {
        double newBalance = balance - valor;
        if (newBalance < ZERO) {
            throw new DomainBusinessException("Saldo insuficiente");
        }
        this.balance = newBalance;
    }

    public void includeDependent(final Dependent dependent) {
        throw new DomainBusinessException("Conta de Poupança não pode ter dependentes");
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CheckingAccount) {
            SavingsAccount account = (SavingsAccount) o;
            return isTheSameLongFields(account)
                    && isTheSameStringFields(account);
        }
        return false;

    }

    @Override
    public int hashCode() {
        return Objects.hash(number, agency, holderCPF);
    }

    private boolean isTheSameLongFields(SavingsAccount account){
        return number == account.number
                && agency == account.agency;
    }

    private boolean isTheSameStringFields(SavingsAccount account){
        return Objects.equals(holderCPF, account.holderCPF);
    }


}
