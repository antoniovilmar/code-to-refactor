package com.example.demo.domain;

import com.example.demo.infrastructure.repository.RestricaoRepository;

public class ValidacaoAberturaContaCorrente {

  private RestricaoRepository restricaoRepository;

  public ValidacaoAberturaContaCorrente(RestricaoRepository restricaoRepository) {
    this.restricaoRepository = restricaoRepository;
  }

  public void validar(String cpf) {
    boolean temRestricao = this.restricaoRepository.temRestricao(cpf,
        TipoRestricao.PENDENCIA_FINANCEIRA_PROPRIO_BANCO);
    if (temRestricao) {
      throw new DomainBusinessException(
          "Não é possível abrir a conta, pois tem uma restrição financeira no próprio banco!");
    }
  }
}
