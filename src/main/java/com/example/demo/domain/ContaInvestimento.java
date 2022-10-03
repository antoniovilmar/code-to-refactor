package com.example.demo.domain;

public class ContaInvestimento extends ContaCorrentePoupanca {

  public ContaInvestimento(String cpfTitular, long numeroConta) {
    super(validacaoAberturaContaCorrente, cpfTitular, TipoConta.INVESTIMENTO, numeroConta, 1L);
  }

  @Override
  public void sacar(double valor) {
    throw new DomainBusinessException("Conta de Investimento não pode sacar, somente transferir");
  }

  @Override
  public void depositar(double valor) {
    throw new DomainBusinessException("Conta de Investimento não pode depositar, somente transferir");
  }

  @Override
  public void incluirDependente(String cpf) {
    throw new DomainBusinessException("Conta Investimento não pode ter dependente");
  }
}
