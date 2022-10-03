package com.example.demo.domain.conta;

import com.example.demo.domain.TipoConta;

import javax.persistence.Entity;

@Entity
public class ContaCorrente extends Conta {

  private ContaCorrente() {
    super();
  }
  private String cpfDependente;

  public ContaCorrente(ValidacaoAberturaContaCorrente validacaoAberturaContaCorrente, long number,
      long agencia, String cpf) {
    super(number, agencia, cpf, TipoConta.CORRENTE);
    validacaoAberturaContaCorrente.validar(cpf);
  }

  @Override
  protected double getSaldoMinimoPermitido() {
    return -1000;
  }

}
