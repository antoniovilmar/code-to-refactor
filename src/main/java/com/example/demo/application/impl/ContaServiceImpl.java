package com.example.demo.application.impl;

import com.example.demo.api.dto.AberturaContaDto;
import com.example.demo.api.dto.TransacaoContaDto;
import com.example.demo.application.ContaService;
import com.example.demo.domain.Conta;
import com.example.demo.domain.TipoConta;
import com.example.demo.domain.Dependente;
import com.example.demo.domain.strategy.CriacaoContaStrategy;
import com.example.demo.infrastructure.ContaRepository;
import com.example.demo.infrastructure.external.GeradorNumeroContaInvestimentoCliente;
import com.example.demo.infrastructure.external.GeradorNumeroContaCliente;

import java.util.List;


public class ContaServiceImpl implements ContaService {

    private final ContaRepository contaRepository;
    private final List<CriacaoContaStrategy> accountsCreation;
    private final GeradorNumeroContaCliente geradorNumeroContaCliente;
    private final GeradorNumeroContaInvestimentoCliente geradorNumeroContaInvestimentoCliente;

    public ContaServiceImpl(ContaRepository contaRepository,
                            List<CriacaoContaStrategy> accountsCreation,
                            GeradorNumeroContaCliente geradorNumeroContaCliente,
                            GeradorNumeroContaInvestimentoCliente geradorNumeroContaInvestimentoCliente) {
        this.contaRepository = contaRepository;
        this.accountsCreation = accountsCreation;
        this.geradorNumeroContaCliente = geradorNumeroContaCliente;
        this.geradorNumeroContaInvestimentoCliente = geradorNumeroContaInvestimentoCliente;
    }

    public long openAccount(final AberturaContaDto aberturaContaDto) {
        TipoConta tipoConta = aberturaContaDto.getAccountType();

        long finalAccountNumber = generateAccountNumber(aberturaContaDto);

        return accountsCreation.stream()
                .filter(accountCreation -> accountCreation.ifAccountType(tipoConta))
                .map(accountCreation -> {
                    Conta newConta =  accountCreation.create(finalAccountNumber,
                            aberturaContaDto.getAgency(),
                            aberturaContaDto.getHolderCPF());
                    return contaRepository.save(newConta).getNumero();
                })
                .findFirst()
                .orElse(0L);
        //talvez aqui seja melhor somente salvar e dar um get para o id
    }

    private long generateAccountNumber(AberturaContaDto aberturaContaDto){
        long accountNumber = geradorNumeroContaCliente.generate();
        if (TipoConta.INVESTIMENTO.equals(aberturaContaDto.getAccountType())) {
            accountNumber = geradorNumeroContaInvestimentoCliente
                    .generate(aberturaContaDto.getHolderCPF());
        }
        return accountNumber;
    }

    public void sacar(TransacaoContaDto transacaoContaDto) {
        // invest NAO saca
        Conta conta = contaRepository.getById(transacaoContaDto.getNumeroConta());
        conta.getTipoConta();
       // conta.sacar(value);
       // contaRepository.save(conta)
    }

    public void depositar(final long accountNumber, final double value) {
        //todas depositam
        Conta conta = contaRepository.getById(accountNumber);
        //conta.(value);
        contaRepository.save(conta);
    }

    public void incluirDependentes(final Dependente dependente, final long accountNumber) {
        //somente conta corrente
        Conta conta = contaRepository.getById(accountNumber);
       // conta.includeDependent(dependente);

    }
}
