package com.example.demo.domain;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class ContaCorrentePoupanca {

  @Id
  private long numero;
  private long agencia;
  private String cpfTitular;
  private String cpfDependente;
  protected double saldo;

  @Enumerated(EnumType.STRING)
  private TipoConta tipoConta;

  @Transient
  private double LIMITE_CHEQUE_ESPECIAL = -1000;

  public ContaCorrentePoupanca(ValidacaoAberturaContaCorrente validacaoAberturaContaCorrente,
      final String cpfTitular, final TipoConta tipoConta,
      final long numeroConta,
      final long agencia) {
    if (TipoConta.CORRENTE.equals(tipoConta)) {
      validacaoAberturaContaCorrente.validar(cpfTitular);
    }
    this.cpfTitular = cpfTitular;
    this.tipoConta = tipoConta;
    this.numero = numeroConta;
    this.agencia = agencia;
  }

  public void depositar(final double valor) {
    this.saldo += valor;
  }

  public void sacar(double valor) {

    double novoSaldo = saldo - valor;

    if (TipoConta.POUPANCA.equals(this.tipoConta)) {
      if (novoSaldo < 0) {
        throw new DomainBusinessException("Saldo insuficiente");
      }
    } else {
      if (novoSaldo < LIMITE_CHEQUE_ESPECIAL) {
        throw new DomainBusinessException("Saldo insuficiente");
      }
    }

    this.saldo = novoSaldo;
  }

  public void incluirDependente(String cpf) {
    if (TipoConta.POUPANCA.equals(
        this.tipoConta)) {
      throw new DomainBusinessException("Conta Poupança não pode ter dependente");
    }
    this.cpfDependente = cpf;

  }

  public double getSaldo() {
    return saldo;
  }

  public long getNumero() {
    return numero;
  }

  public long getAgencia() {
    return agencia;
  }

  public TipoConta getTipoConta() {
    return tipoConta;
  }

  public String getCpfDependente() {
    return cpfDependente;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ContaCorrentePoupanca)) {
      return false;
    }
    ContaCorrentePoupanca contaCorrentePoupanca = (ContaCorrentePoupanca) o;
    return getNumero() == contaCorrentePoupanca.getNumero();
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getNumero());
  }

}
