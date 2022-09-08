package com.example.demo.application.impl;

import com.example.demo.api.dto.AberturaContaDto;
import com.example.demo.application.ContaService;
import com.example.demo.domain.Conta;
import com.example.demo.domain.AccountType;
import com.example.demo.domain.Dependente;
import com.example.demo.domain.strategy.AccountCreationStrategy;
import com.example.demo.infrastructure.AccountRepository;
import com.example.demo.infrastructure.external.GeradorNumeroContaInvestimentoCliente;
import com.example.demo.infrastructure.external.GeradorNumeroContaCliente;

import java.util.List;


public class ContaServiceImpl implements ContaService {

    private final AccountRepository accountRepository;
    private final List<AccountCreationStrategy> accountsCreation;
    private final GeradorNumeroContaCliente geradorNumeroContaCliente;
    private final GeradorNumeroContaInvestimentoCliente geradorNumeroContaInvestimentoCliente;

    public ContaServiceImpl(AccountRepository accountRepository,
                            List<AccountCreationStrategy> accountsCreation,
                            GeradorNumeroContaCliente geradorNumeroContaCliente,
                            GeradorNumeroContaInvestimentoCliente geradorNumeroContaInvestimentoCliente) {
        this.accountRepository = accountRepository;
        this.accountsCreation = accountsCreation;
        this.geradorNumeroContaCliente = geradorNumeroContaCliente;
        this.geradorNumeroContaInvestimentoCliente = geradorNumeroContaInvestimentoCliente;
    }

    public long openAccount(final AberturaContaDto aberturaContaDto) {
        AccountType accountType = aberturaContaDto.getAccountType();

        long finalAccountNumber = generateAccountNumber(aberturaContaDto);

        return accountsCreation.stream()
                .filter(accountCreation -> accountCreation.ifAccountType(accountType))
                .map(accountCreation -> {
                    Conta newConta =  accountCreation.create(finalAccountNumber,
                            aberturaContaDto.getAgency(),
                            aberturaContaDto.getHolderCPF());
                    return accountRepository.save(newConta).getNumber();
                })
                .findFirst()
                .orElse(0L);
        //talvez aqui seja melhor somente salvar e dar um get para o id
    }

    private long generateAccountNumber(AberturaContaDto aberturaContaDto){
        long accountNumber = geradorNumeroContaCliente.generate();
        if (AccountType.INVESTMENT.equals(aberturaContaDto.getAccountType())) {
            accountNumber = geradorNumeroContaInvestimentoCliente
                    .generate(aberturaContaDto.getHolderCPF());
        }
        return accountNumber;
    }

    public void withdraw(final long accountNumber, final double value) {
        Conta conta = accountRepository.getById(accountNumber);
        conta.withdraw(value);
        accountRepository.save(conta);
    }

    public void deposit(final long accountNumber, final double value) {
        Conta conta = accountRepository.getById(accountNumber);
        conta.deposit(value);
        accountRepository.save(conta);
    }

    public void includeDependent(final Dependente dependente, final long accountNumber) {
        Conta conta = accountRepository.getById(accountNumber);
        conta.includeDependent(dependente);

    }
}
