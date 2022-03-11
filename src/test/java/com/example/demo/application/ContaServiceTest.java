package com.example.demo.application;

import com.example.demo.api.DadosAberturaConta;
import com.example.demo.domain.Conta;
import com.example.demo.domain.TipoConta;
import com.example.demo.infrastructure.ContaRepository;
import com.example.demo.infrastructure.external.GeradorNumeroContaClient;
import com.example.demo.infrastructure.external.GeradorNumeroContaInvestimentoClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContaServiceTest {

    @Mock
    private ContaRepository contaRepository;
    @Mock
    private GeradorNumeroContaInvestimentoClient geradorNumeroContaInvestimentoClient;
    @Mock
    private GeradorNumeroContaClient geradorNumeroContaClient;

    @InjectMocks
    private ContaService contaService;

    @Test
    @DisplayName("Deve abrir conta corrente")
    void deveAbrirContaCorrente() {
        var contaEsperada = new Conta("90909090", TipoConta.CORRENTE, 123, 1L);

        doReturn(contaEsperada).when(contaRepository).save(contaEsperada);
        doReturn(123L).when(geradorNumeroContaClient).gerar();

        var dadosAberturaConta = new DadosAberturaConta(1L, "90909090");
        long numeroContaCriada = contaService.abrirConta(dadosAberturaConta, TipoConta.CORRENTE);

        Assertions.assertEquals(123, numeroContaCriada);

    }
}