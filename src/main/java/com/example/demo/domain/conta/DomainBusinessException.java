package com.example.demo.domain.conta;

public class DomainBusinessException extends RuntimeException {

  public DomainBusinessException(String mensagem) {
    super(mensagem);
  }
}
