package com.example.demo.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ContaTest {

    @Test
    @DisplayName("Deve permitir saque, quando é conta corrente")
    void devePermitirSaqueQuandoParaContaCorrente() {
        Conta conta = new Conta("99999999", TipoConta.CORRENTE, 123L, 1L);
        conta.sacar(10);

        Assertions.assertEquals(-10, conta.getSaldo());
    }

    @Test
    @DisplayName("Deve permitir saque para conta poupanca")
    void devePermitirSaqueParaContaPoupanca() {
        Conta conta = new Conta("99999999", TipoConta.POUPANCA, 123L, 1L);
        conta.depositar(100);
        conta.sacar(10);

        Assertions.assertEquals(90, conta.getSaldo());
    }

    @Test
    @DisplayName("Não deve permitir efetuar o saque, quando é conta investimento")
    void naoDevePermitirSaqueParaContaInvestimento() {
        Conta conta = new Conta("99999999", TipoConta.INVESTIMENTO, 123L, 1L);

        assertThrows(
                DomainBusinessException.class,
                () -> conta.sacar(10)
        );
    }

    @Test
    @DisplayName("Não deve permitir a inclusão de dependente para conta de investimento")
    void naoDevePermitirAInclusaoDeDependenteParaContaInvestimento() {
        Conta conta = new Conta("99999999", TipoConta.INVESTIMENTO, 123L, 1L);

        assertThrows(
                DomainBusinessException.class,
                () -> conta.incluirDependente("1234", "51888888"));

    }

    @Test
    @DisplayName("Não deve permitir a inclusão de dependente para conta poupança")
    void naoDevePermitirAInclusaoDeDependenteParaContaPoupanca() {
        Conta conta = new Conta("99999999", TipoConta.POUPANCA, 123L, 1L);

        assertThrows(
                DomainBusinessException.class,
                () -> conta.incluirDependente("1234", "51888888"));
    }

    @Test
    @DisplayName("Deve incluir dependente para conta corrente")
    void deveInclusaoDependenteParaContaCorrente() {
        Conta conta = new Conta("99999999", TipoConta.CORRENTE, 123L, 1L);
        conta.incluirDependente("1234", "51888888");
    }

    @Test
    @DisplayName("Deve incluir dependente para conta corrente")
    void deveRemoverDependenteParaContaCorrente() {
        Conta conta = new Conta("99999999", TipoConta.CORRENTE, 123L, 1L);
        conta.incluirDependente("1234", "51888888");
        conta.removerDependente("1234");

        Assertions.assertTrue(conta.getDependentes().isEmpty());
    }

    @Test
    @DisplayName("Não deve permitir saque, quando ultrapassa o cheque especial de mil reais para conta corrente")
    void naoDevePermitirSaqueQuandoUltrapassaChequeEspecialParaContaCorrente() {
        Conta conta = new Conta("99999999", TipoConta.CORRENTE, 123L, 1L);

        assertThrows(
                DomainBusinessException.class,
                () -> conta.sacar(1001)
        );
    }

    @Test
    @DisplayName("Não deve permitir saque, quando saldo fica negativo para conta poupanca")
    void naoDevePermitirSaqueQuandoSaldoFicaNegativoParaContaPoupanca() {
        Conta conta = new Conta("99999999", TipoConta.POUPANCA, 123L, 1L);

        assertThrows(
                DomainBusinessException.class,
                () -> conta.sacar(1)
        );
    }

    @Test
    void deveTestarEqualsAndHashCode() {
        EqualsVerifier.simple().forClass(Conta.class)
                .suppress(Warning.SURROGATE_KEY)
                .verify();
    }

}