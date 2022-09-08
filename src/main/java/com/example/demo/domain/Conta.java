package com.example.demo.domain;

import javax.persistence.Id;

public abstract class Conta implements ContaBase {
    @Id
    private long number;
    private long agency;
    private String holderCPF;
    private double balance;

    protected Conta(long number, long agency, String holderCPF) {
        this.number = number;
        this.agency = agency;
        this.holderCPF = holderCPF;
    }

    public long getNumber() {
        return number;
    }

    public long getAgency() {
        return agency;
    }

    public String getHolderCPF() {
        return holderCPF;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
