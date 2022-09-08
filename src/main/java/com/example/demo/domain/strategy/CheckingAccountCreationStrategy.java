package com.example.demo.domain.strategy;

import com.example.demo.domain.Conta;
import com.example.demo.domain.AccountType;
import com.example.demo.domain.impl.ContaCorrente;

public class CheckingAccountCreationStrategy implements AccountCreationStrategy {
    @Override
    public Boolean ifAccountType(AccountType accountType) {
        return accountType.equals(AccountType.CHECKING);
    }

    @Override
    public Conta create(long number, long agency, String holderCPF) {
        return new ContaCorrente(number, agency, holderCPF);
    }
}
