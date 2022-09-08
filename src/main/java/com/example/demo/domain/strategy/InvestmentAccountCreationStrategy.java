package com.example.demo.domain.strategy;

import com.example.demo.domain.Conta;
import com.example.demo.domain.AccountType;
import com.example.demo.domain.impl.ContaInvestimento;

public class InvestmentAccountCreationStrategy implements AccountCreationStrategy {
    @Override
    public Boolean ifAccountType(AccountType accountType) {
        return accountType.equals(AccountType.SAVINGS);
    }

    @Override
    public Conta create(long number, long agency, String holderCPF) {
        return new ContaInvestimento(number, agency, holderCPF);
    }
}
