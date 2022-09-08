package com.example.demo.application;

import com.example.demo.application.impl.ContaServiceImpl;
import com.example.demo.domain.strategy.AccountCreationStrategy;
import com.example.demo.domain.strategy.CheckingAccountCreationStrategy;
import com.example.demo.domain.strategy.InvestmentAccountCreationStrategy;
import com.example.demo.domain.strategy.SavingsAccountCreationStrategy;
import com.example.demo.infrastructure.AccountRepository;
import com.example.demo.infrastructure.external.GeradorNumeroContaInvestimentoCliente;
import com.example.demo.infrastructure.external.GeradorNumeroContaCliente;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ContaConfig {
    @Bean
    public ContaService accountService(AccountRepository accountRepository,
                                       List<AccountCreationStrategy> accountCreationStrategy,
                                       GeradorNumeroContaCliente geradorNumeroContaCliente,
                                       GeradorNumeroContaInvestimentoCliente geradorNumeroContaInvestimentoCliente) {
        return new ContaServiceImpl(accountRepository, accountCreationStrategy,
                geradorNumeroContaCliente, geradorNumeroContaInvestimentoCliente);
    }



    @Bean
    public List<AccountCreationStrategy> accountCreationStrategy(
            CheckingAccountCreationStrategy checkingAccountCreationStrategy,
            InvestmentAccountCreationStrategy investmentAccountCreationStrategy,
            SavingsAccountCreationStrategy savingsAccountCreationStrategy) {

        List<AccountCreationStrategy> accountsCreation = new ArrayList<>();
        accountsCreation.add(checkingAccountCreationStrategy);
        accountsCreation.add(investmentAccountCreationStrategy);
        accountsCreation.add(savingsAccountCreationStrategy);

        return accountsCreation;
    }

}
