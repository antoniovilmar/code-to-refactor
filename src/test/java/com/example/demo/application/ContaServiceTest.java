package com.example.demo.application;

import com.example.demo.api.dto.AberturaContaDto;
import com.example.demo.domain.TipoConta;
import com.example.demo.domain.conta.ContaCorrente;
import com.example.demo.infrastructure.ContaRepository;
import com.example.demo.infrastructure.external.GeradorNumeroContaFachada;
import com.example.demo.infrastructure.external.GeradorNumeroContaInvestimentoFachada;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


class ContaServiceTest {
//
//    private ContaService contaService;
//    private ContaRepository contaRepository;
//    private CriacaoContaStrategy accountCreationOne;
//    private CriacaoContaStrategy accountCreationTwo;
//    private CriacaoContaStrategy accountCreationThree;
//    private GeradorNumeroContaFachada geradorNumeroContaFachada;
//    private GeradorNumeroContaInvestimentoFachada geradorNumeroContaInvestimentoFachada;
//
//    @BeforeEach
//    public void init() {
//        contaRepository = mock(ContaRepository.class);
//
//        accountCreationOne = mock(CriacaoContaStrategy.class);
//        accountCreationTwo = mock(CriacaoContaStrategy.class);
//
//        List<CriacaoContaStrategy> accountsCreation = new ArrayList<>();
//        accountsCreation.add(accountCreationOne);
//        accountsCreation.add(accountCreationTwo);
//
//        geradorNumeroContaFachada = mock(GeradorNumeroContaFachada.class);
//        geradorNumeroContaInvestimentoFachada = mock(GeradorNumeroContaInvestimentoFachada.class);
//
//
//        contaService = new ContaServiceImpl(contaRepository,
//                accountsCreation,
//                geradorNumeroContaFachada,
//                geradorNumeroContaInvestimentoFachada);
//    }
//
//
//    @Test
//    @DisplayName("Deve abrir conta corrente")
//    void deveAbrirContaCorrente() {
//        long numeroConta = 28L;
//        long agencia = 123L;
//        String cpfTitular = "028";
//
//        var contaEsperada = new ContaCorrente(numeroConta,agencia, cpfTitular);
//        AberturaContaDto dadosAberturaConta = new AberturaContaDto(agencia,cpfTitular);
//        dadosAberturaConta.setAccountType(TipoConta.CORRENTE);
//        var contaCriada = new ContaCorrente(numeroConta,agencia, cpfTitular);
//
//        when(geradorNumeroContaFachada.gerar()).thenReturn(numeroConta);
//        when(accountCreationOne.ifAccountType(TipoConta.CORRENTE)).thenReturn(true);
//        when(accountCreationOne.create(numeroConta, agencia, cpfTitular)).thenReturn(contaEsperada);
//        when(contaRepository.save(contaEsperada)).thenReturn(contaCriada);
//
//
//        long numeroContaCriada = contaService.openAccount(dadosAberturaConta);
//        Assertions.assertEquals(numeroConta, numeroContaCriada);
//
//        //balance nao ta sendo setado é 0?
//
//    }
}