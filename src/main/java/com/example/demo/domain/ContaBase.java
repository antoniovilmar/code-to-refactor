package com.example.demo.domain;


public interface ContaBase {
    void deposit(final double value);

    void withdraw(double value);

    void includeDependent(final Dependente dependente) ;

    // 3 interfaces
}
