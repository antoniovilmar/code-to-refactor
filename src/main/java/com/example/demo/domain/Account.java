package com.example.demo.domain;


public interface Account {
    long getNumber();

    long getAgency();

    double getBalance();

    String getHolderCPF();

    void deposit(final double value);

    void withdraw(double value);

    void includeDependent(final Dependent dependent) ;
}
