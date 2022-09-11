package com.example.demo.application;

import com.example.demo.api.DadosAberturaContaDto;
import com.example.demo.domain.Conta;
import com.example.demo.domain.TipoConta;
import com.example.demo.infrastructure.ContaRepository;
import com.example.demo.infrastructure.external.GeradorNumeroContaFachada;
import com.example.demo.infrastructure.external.GeradorNumeroContaInvestimentoFachada;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ContaServiceTest {

    @Mock
    private ContaRepository contaRepository;
    @Mock
    private GeradorNumeroContaInvestimentoFachada geradorNumeroContaInvestimentoFachada;
    @Mock
    private GeradorNumeroContaFachada geradorNumeroContaFachada;

    @InjectMocks
    private ContaService contaService;

    @Test
    @DisplayName("Deve abrir conta corrente")
    void deveAbrirContaCorrente() {
        var contaEsperada = new Conta("90909090", TipoConta.CORRENTE, 123, 1L);

        doReturn(contaEsperada).when(contaRepository).save(contaEsperada);
        doReturn(123L).when(geradorNumeroContaFachada).gerar(TipoConta.CORRENTE);

        var dadosAberturaConta = new DadosAberturaContaDto(1L, "90909090");
        long numeroContaCriada = contaService.abrirConta(dadosAberturaConta, TipoConta.CORRENTE);

        Assertions.assertEquals(123, numeroContaCriada);

    }
}