package com.example.demo.application;

import com.example.demo.api.dto.AberturaContaDto;
import com.example.demo.application.impl.ContaServiceImpl;
import com.example.demo.domain.TipoConta;
import com.example.demo.domain.impl.ContaCorrente;
import com.example.demo.domain.strategy.CriacaoContaStrategy;
import com.example.demo.infrastructure.ContaRepository;
import com.example.demo.infrastructure.external.GeradorNumeroContaCliente;
import com.example.demo.infrastructure.external.GeradorNumeroContaInvestimentoCliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


class ContaServiceTest {

    private ContaService contaService;
    private ContaRepository contaRepository;
    private CriacaoContaStrategy accountCreationOne;
    private CriacaoContaStrategy accountCreationTwo;
    private CriacaoContaStrategy accountCreationThree;
    private GeradorNumeroContaCliente geradorNumeroContaCliente;
    private GeradorNumeroContaInvestimentoCliente geradorNumeroContaInvestimentoCliente;

    @BeforeEach
    public void init() {
        contaRepository = mock(ContaRepository.class);

        accountCreationOne = mock(CriacaoContaStrategy.class);
        accountCreationTwo = mock(CriacaoContaStrategy.class);

        List<CriacaoContaStrategy> accountsCreation = new ArrayList<>();
        accountsCreation.add(accountCreationOne);
        accountsCreation.add(accountCreationTwo);

        geradorNumeroContaCliente = mock(GeradorNumeroContaCliente.class);
        geradorNumeroContaInvestimentoCliente = mock(GeradorNumeroContaInvestimentoCliente.class);


        contaService = new ContaServiceImpl(contaRepository,
                accountsCreation,
                geradorNumeroContaCliente,
                geradorNumeroContaInvestimentoCliente);
    }


    @Test
    @DisplayName("Deve abrir conta corrente")
    void deveAbrirContaCorrente() {
        long numeroConta = 28L;
        long agencia = 123L;
        String cpfTitular = "028";

        var contaEsperada = new ContaCorrente(numeroConta,agencia, cpfTitular);
        AberturaContaDto dadosAberturaConta = new AberturaContaDto(agencia,cpfTitular);
        dadosAberturaConta.setAccountType(TipoConta.CORRENTE);
        var contaCriada = new ContaCorrente(numeroConta,agencia, cpfTitular);

        when(geradorNumeroContaCliente.generate()).thenReturn(numeroConta);
        when(accountCreationOne.ifAccountType(TipoConta.CORRENTE)).thenReturn(true);
        when(accountCreationOne.create(numeroConta, agencia, cpfTitular)).thenReturn(contaEsperada);
        when(contaRepository.save(contaEsperada)).thenReturn(contaCriada);


        long numeroContaCriada = contaService.openAccount(dadosAberturaConta);
        Assertions.assertEquals(numeroConta, numeroContaCriada);

        //balance nao ta sendo setado é 0?

    }
}