package com.example.demo.domain.strategy;

import com.example.demo.domain.Conta;
import com.example.demo.domain.AccountType;
import com.example.demo.domain.impl.ContaPoupanca;

public class SavingsAccountCreationStrategy implements AccountCreationStrategy {
    @Override
    public Boolean ifAccountType(AccountType accountType) {
        return accountType.equals(AccountType.SAVINGS);
    }

    @Override
    public Conta create(long number, long agency, String holderCPF) {
        return new ContaPoupanca(number, agency, holderCPF);
    }
}
