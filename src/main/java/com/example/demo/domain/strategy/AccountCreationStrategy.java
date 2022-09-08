package com.example.demo.domain.strategy;

import com.example.demo.domain.Conta;
import com.example.demo.domain.AccountType;

public interface AccountCreationStrategy {
    Boolean ifAccountType(AccountType accountType);
    Conta create(long number, long agency, String holderCPF);

    //concatenar com template method junto ver!!
}
