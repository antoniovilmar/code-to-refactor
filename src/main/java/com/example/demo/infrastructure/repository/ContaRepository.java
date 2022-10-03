package com.example.demo.infrastructure.repository;

import com.example.demo.domain.ContaCorrentePoupanca;
import org.springframework.stereotype.Repository;

@Repository
public class ContaRepository {

  private ContaSpringData data;

  public ContaRepository(ContaSpringData data) {
    this.data = data;
  }

  public ContaCorrentePoupanca obter(long numeroConta) {
    return this.data.getById(numeroConta);
  }

  public ContaCorrentePoupanca salvar(ContaCorrentePoupanca conta) {
    return this.data.save(conta);
  }
}
