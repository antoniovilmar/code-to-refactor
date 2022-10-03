package com.example.demo.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ContaCorrentePoupancaTest {

  @Test
  @DisplayName("Deve permitir saque, quando é conta corrente")
  void devePermitirSaqueQuandoParaContaCorrente() {
    ContaCorrentePoupanca contaCorrentePoupanca = new ContaCorrentePoupanca(
        validacaoAberturaContaCorrente, "99999999", TipoConta.CORRENTE, 123L, 1L);
    contaCorrentePoupanca.sacar(10);

    Assertions.assertEquals(-10, contaCorrentePoupanca.getSaldo());
  }

  @Test
  @DisplayName("Deve permitir saque para conta poupanca")
  void devePermitirSaqueParaContaPoupanca() {
    ContaCorrentePoupanca contaCorrentePoupanca = new ContaCorrentePoupanca(
        validacaoAberturaContaCorrente, "99999999", TipoConta.POUPANCA, 123L, 1L);
    contaCorrentePoupanca.depositar(100);
    contaCorrentePoupanca.sacar(10);

    Assertions.assertEquals(90, contaCorrentePoupanca.getSaldo());
  }

  @Test
  @DisplayName("Não deve permitir efetuar o saque, quando é conta investimento")
  void   naoDevePermitirSaqueParaContaInvestimento() {
    ContaCorrentePoupanca contaCorrentePoupanca = new ContaCorrentePoupanca(
        validacaoAberturaContaCorrente, "99999999", TipoConta.INVESTIMENTO, 123L, 1L);

    assertThrows(
        DomainBusinessException.class,
        () -> contaCorrentePoupanca.sacar(10)
    );
  }

  @Test
  @DisplayName("Não deve permitir a inclusão de dependente para conta de investimento")
  void naoDevePermitirAInclusaoDeDependenteParaContaInvestimento() {
    ContaCorrentePoupanca contaCorrentePoupanca = new ContaCorrentePoupanca(
        validacaoAberturaContaCorrente, "99999999", TipoConta.INVESTIMENTO, 123L, 1L);

    assertThrows(
        DomainBusinessException.class,
        () -> contaCorrentePoupanca.incluirDependente("1234"));

  }

  @Test
  @DisplayName("Não deve permitir a inclusão de dependente para conta poupança")
  void naoDevePermitirAInclusaoDeDependenteParaContaPoupanca() {
    ContaCorrentePoupanca contaCorrentePoupanca = new ContaCorrentePoupanca(
        validacaoAberturaContaCorrente, "99999999", TipoConta.POUPANCA, 123L, 1L);

    assertThrows(
        DomainBusinessException.class,
        () -> contaCorrentePoupanca.incluirDependente("1234"));
  }

  @Test
  @DisplayName("Deve incluir dependente para conta corrente")
  void deveInclusaoDependenteParaContaCorrente() {
    ContaCorrentePoupanca contaCorrentePoupanca = new ContaCorrentePoupanca(
        validacaoAberturaContaCorrente, "99999999", TipoConta.CORRENTE, 123L, 1L);
    contaCorrentePoupanca.incluirDependente("1234");
  }

  @Test
  @DisplayName("Deve incluir dependente para conta corrente")
  void deveRemoverDependenteParaContaCorrente() {
    ContaCorrentePoupanca contaCorrentePoupanca = new ContaCorrentePoupanca(
        validacaoAberturaContaCorrente, "99999999", TipoConta.CORRENTE, 123L, 1L);
    contaCorrentePoupanca.incluirDependente("1234");
//    conta.removerDependente();
//
//    Assertions.assertTrue(conta.getCpfDependentes().isEmpty());
  }

  @Test
  @DisplayName("Não deve permitir saque, quando ultrapassa o cheque especial de mil reais para conta corrente")
  void naoDevePermitirSaqueQuandoUltrapassaChequeEspecialParaContaCorrente() {
    ContaCorrentePoupanca contaCorrentePoupanca = new ContaCorrentePoupanca(
        validacaoAberturaContaCorrente, "99999999", TipoConta.CORRENTE, 123L, 1L);

    assertThrows(
        DomainBusinessException.class,
        () -> contaCorrentePoupanca.sacar(1001)
    );
  }

  @Test
  @DisplayName("Não deve permitir saque, quando saldo fica negativo para conta poupanca")
  void naoDevePermitirSaqueQuandoSaldoFicaNegativoParaContaPoupanca() {
    ContaCorrentePoupanca contaCorrentePoupanca = new ContaCorrentePoupanca(
        validacaoAberturaContaCorrente, "99999999", TipoConta.POUPANCA, 123L, 1L);

    assertThrows(
        DomainBusinessException.class,
        () -> contaCorrentePoupanca.sacar(1)
    );
  }

  @Test
  void deveTestarEqualsAndHashCode() {
    EqualsVerifier.simple().forClass(ContaCorrentePoupanca.class)
        .suppress(Warning.SURROGATE_KEY)
        .verify();
  }

}