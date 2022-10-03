package com.example.demo.application.conta.corrente;

import com.example.demo.api.dto.AberturaContaDto;
import com.example.demo.application.conta.AberturaContaService;
import com.example.demo.domain.TipoConta;
import com.example.demo.domain.conta.Conta;
import com.example.demo.domain.conta.ContaCorrente;
import com.example.demo.domain.conta.ContaRepository;
import com.example.demo.domain.conta.ValidacaoAberturaContaCorrente;
import com.example.demo.infrastructure.external.GeradorNumeroConta;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("Corrente")
public class AberturaContaCorrenteServiceImpl implements AberturaContaService {

  private final GeradorNumeroConta geradorNumeroConta;

  private ValidacaoAberturaContaCorrente validacaoAberturaContaCorrente;
  private final ContaRepository contaRepository;

  public AberturaContaCorrenteServiceImpl(GeradorNumeroConta geradorNumeroConta,
      ValidacaoAberturaContaCorrente validacaoAberturaContaCorrente, ContaRepository contaRepository) {
    this.geradorNumeroConta = geradorNumeroConta;
    this.validacaoAberturaContaCorrente = validacaoAberturaContaCorrente;
    this.contaRepository = contaRepository;
  }

  @Override
  public long abrir(AberturaContaDto aberturaContaDto) {
    final long numeroConta = geradorNumeroConta.gerar(TipoConta.CORRENTE);
    final Conta contaParaCriar = new ContaCorrente(validacaoAberturaContaCorrente, numeroConta,
        aberturaContaDto.getAgencia(), aberturaContaDto.getCpfTitular());
    final Conta contaCriada = contaRepository.salvar(contaParaCriar);

    return contaCriada.getNumero();
  }

}
