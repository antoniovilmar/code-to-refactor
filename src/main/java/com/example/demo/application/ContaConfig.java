package com.example.demo.application;

import com.example.demo.application.impl.ContaServiceImpl;
import com.example.demo.domain.strategy.CriacaoContaStrategy;
import com.example.demo.domain.strategy.CriacaoContaCorrenteStrategy;
import com.example.demo.domain.strategy.CriacaoContaInvestimentoStrategy;
import com.example.demo.domain.strategy.CriacaoContaPoupancaStrategy;
import com.example.demo.infrastructure.ContaRepository;
import com.example.demo.infrastructure.external.GeradorNumeroContaInvestimentoCliente;
import com.example.demo.infrastructure.external.GeradorNumeroContaCliente;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ContaConfig {
    @Bean
    public ContaService accountService(ContaRepository contaRepository,
                                       List<CriacaoContaStrategy> criacaoContaStrategy,
                                       GeradorNumeroContaCliente geradorNumeroContaCliente,
                                       GeradorNumeroContaInvestimentoCliente geradorNumeroContaInvestimentoCliente) {
        return new ContaServiceImpl(contaRepository, criacaoContaStrategy,
                geradorNumeroContaCliente, geradorNumeroContaInvestimentoCliente);
    }

    @Bean
    public List<CriacaoContaStrategy> accountCreationStrategy(
            CriacaoContaCorrenteStrategy checkingAccountCreationStrategy,
            CriacaoContaInvestimentoStrategy investmentAccountCreationStrategy,
            CriacaoContaPoupancaStrategy savingsAccountCreationStrategy) {

        List<CriacaoContaStrategy> accountsCreation = new ArrayList<>();
        accountsCreation.add(checkingAccountCreationStrategy);
        accountsCreation.add(investmentAccountCreationStrategy);
        accountsCreation.add(savingsAccountCreationStrategy);

        return accountsCreation;
    }

}
