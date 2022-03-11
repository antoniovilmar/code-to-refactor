package com.example.demo.application;

import com.example.demo.api.DadosAberturaConta;
import com.example.demo.domain.Conta;
import com.example.demo.domain.Dependente;
import com.example.demo.domain.TipoConta;
import com.example.demo.infrastructure.ContaRepository;
import com.example.demo.infrastructure.external.GeradorNumeroContaClient;
import com.example.demo.infrastructure.external.GeradorNumeroContaInvestimentoClient;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    private ContaRepository contaRepository;
    private GeradorNumeroContaClient geradorNumeroContaClient;
    private GeradorNumeroContaInvestimentoClient geradorNumeroContaInvestimentoClient;

    public ContaService(ContaRepository contaRepository, GeradorNumeroContaClient geradorNumeroContaClient, GeradorNumeroContaInvestimentoClient geradorNumeroContaInvestimentoClient) {
        this.contaRepository = contaRepository;
        this.geradorNumeroContaClient = geradorNumeroContaClient;
        this.geradorNumeroContaInvestimentoClient = geradorNumeroContaInvestimentoClient;
    }

    public long abrirConta(final DadosAberturaConta dadosAberturaConta, final TipoConta tipoConta) {
        if (TipoConta.INVESTIMENTO.equals(tipoConta)) {
            long numeroContaInvestimento = geradorNumeroContaInvestimentoClient.gerar(dadosAberturaConta.getCpfTitular());
            Conta conta = new Conta(dadosAberturaConta.getCpfTitular(), tipoConta, numeroContaInvestimento, dadosAberturaConta.getAgencia());
            contaRepository.save(conta);
        }
        long numeroConta = geradorNumeroContaClient.gerar();
        var contaParaCriar = new Conta(dadosAberturaConta.getCpfTitular(), tipoConta, numeroConta, dadosAberturaConta.getAgencia());
        Conta contaCriada = contaRepository.save(contaParaCriar);

        return contaCriada.getNumero();
    }

    public void sacar(final long numeroConta, final double valor) {
        Conta conta = contaRepository.getById(numeroConta);
        conta.sacar(valor);
        contaRepository.save(conta);

    }

    public void depositar(final long numeroConta, final double valor) {
        Conta conta = contaRepository.getById(numeroConta);
        conta.depositar(valor);
        contaRepository.save(conta);
    }

    public void incluirDependente(final Dependente dependente, final long numeroConta) {
        Conta conta = contaRepository.getById(numeroConta);
        conta.incluirDependente(dependente);

    }
}
