package com.example.demo.infrastructure.external;

import com.example.demo.domain.TipoConta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeradorNumeroContaRandomicoImplTest {

    @Test
    @DisplayName("Deve gerar com 6 digitos para conta corrente")
    void deveGerarParaContaCorrente() {
        GeradorNumeroConta geradorNumeroConta = new GeradorNumeroContaRandomicoImpl();

        Long numeroContaCorrente = geradorNumeroConta.gerar(TipoConta.CORRENTE);


        assertEquals(6, numeroContaCorrente.toString().length());
    }

    @Test
    @DisplayName("Deve gerar com 5 digitos para conta poupança")
    void deveGerarParaContaPoupanca() {
        GeradorNumeroConta geradorNumeroConta = new GeradorNumeroContaRandomicoImpl();

        Long numeroContaCorrente = geradorNumeroConta.gerar(TipoConta.POUPANCA);


        assertEquals(5, numeroContaCorrente.toString().length());
    }

}