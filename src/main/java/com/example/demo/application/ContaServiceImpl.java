package com.example.demo.application;

import static java.util.Objects.nonNull;

import com.example.demo.api.DadosContaDto;
import com.example.demo.api.MovimentacaoContaDto;
import com.example.demo.domain.ContaCorrentePoupanca;
import com.example.demo.domain.ContaInvestimento;
import com.example.demo.domain.Movimentacao;
import com.example.demo.domain.Restricao;
import com.example.demo.domain.TipoConta;
import com.example.demo.domain.TipoMovimentacao;
import com.example.demo.domain.TipoRestricao;
import com.example.demo.domain.ValidacaoAberturaContaCorrente;
import com.example.demo.infrastructure.ContaInvestimentoRepository;
import com.example.demo.infrastructure.MovimentacaoRepository;
import com.example.demo.infrastructure.external.ConsultaPerfilRisco;
import com.example.demo.infrastructure.external.GeradorNumeroConta;
import com.example.demo.infrastructure.external.GeradorNumeroContaInvestimento;
import com.example.demo.infrastructure.repository.ContaRepository;
import com.example.demo.infrastructure.repository.LimiteRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.example.demo.infrastructure.repository.RestricaoRepository;

@Service
public class ContaServiceImpl implements ContaService {

  private ContaRepository contaRepository;
  private GeradorNumeroConta geradorNumeroConta;
  private GeradorNumeroContaInvestimento geradorNumeroContaInvestimento;
  private RestricaoRepository restricaoRepository;
  private MovimentacaoRepository movimentacaoRepository;
  private ConsultaPerfilRisco consultaPerfilRisco;
  private LimiteRepository limiteRepository;
  private ContaInvestimentoRepository contaInvestimentoRepository;
  private ValidacaoAberturaContaCorrente validacaoAberturaContaCorrente;

  public ContaServiceImpl(ContaRepository contaRepository, GeradorNumeroConta geradorNumeroConta,
      GeradorNumeroContaInvestimento geradorNumeroContaInvestimento,
      RestricaoRepository restricaoRepository, MovimentacaoRepository movimentacaoRepository,
      ConsultaPerfilRisco consultaPerfilRisco, LimiteRepository limiteRepository,
      ContaInvestimentoRepository contaInvestimentoRepository) {
    this.contaRepository = contaRepository;
    this.geradorNumeroConta = geradorNumeroConta;
    this.geradorNumeroContaInvestimento = geradorNumeroContaInvestimento;
    this.restricaoRepository = restricaoRepository;
    this.movimentacaoRepository = movimentacaoRepository;
    this.consultaPerfilRisco = consultaPerfilRisco;
    this.limiteRepository = limiteRepository;
    this.contaInvestimentoRepository = contaInvestimentoRepository;
  }


  @Override
  public List<TipoRestricao> listarRestricoes(String cpf) {
    return this.restricaoRepository.listar(cpf).stream().map(Restricao::getTipoRestricao).collect(
        Collectors.toList());
  }

  public long abrirConta(final DadosContaDto dadosContaDto,
      final TipoConta tipoConta) {
    if (TipoConta.INVESTIMENTO.equals(tipoConta)) {
      long numeroContaInvestimento = geradorNumeroContaInvestimento.gerar(
          dadosContaDto.getCpfTitular());
      ContaInvestimento contaInvestimento = new ContaInvestimento(
          dadosContaDto.getCpfTitular(),
          numeroContaInvestimento);
      ContaCorrentePoupanca conta = contaRepository.salvar(
          contaInvestimento);

      return conta.getNumero();
    } else {
      long numeroConta = geradorNumeroConta.gerar(tipoConta);
      var conta = new ContaCorrentePoupanca(validacaoAberturaContaCorrente, dadosContaDto.getCpfTitular(), tipoConta,
          numeroConta,
          dadosContaDto.getAgencia());
      if (TipoConta.CORRENTE.equals(tipoConta)) {
        if (nonNull(dadosContaDto.getDependente())) {
          conta.incluirDependente(dadosContaDto.getDependente());
        }
      }
      ContaCorrentePoupanca contaCorrentePoupancaCriada = contaRepository.salvar(conta);

      return contaCorrentePoupancaCriada.getNumero();

    }
  }

  public void movimentar(final long numeroConta,
      final MovimentacaoContaDto movimentacaoContaDto) {
    ContaCorrentePoupanca contaCorrentePoupanca = contaRepository.obter(numeroConta);

    if (TipoMovimentacao.SAQUE.equals(movimentacaoContaDto.getMovimento())) {
      contaCorrentePoupanca.sacar(movimentacaoContaDto.getValor());
    } else {
      contaCorrentePoupanca.depositar(movimentacaoContaDto.getValor());
    }

    contaRepository.salvar(contaCorrentePoupanca);
    Movimentacao movimentacao = new Movimentacao(contaCorrentePoupanca.getNumero(),
        contaCorrentePoupanca.getAgencia(),
        contaCorrentePoupanca.getSaldo(), movimentacaoContaDto.getValor(),
        contaCorrentePoupanca.getTipoConta(),
        movimentacaoContaDto.getMovimento());
    movimentacaoRepository.save(movimentacao);


  }

  public List<String> gerarExtrato(long numeroConta) {
    return this.movimentacaoRepository.findByNumeroConta(numeroConta).stream()
        .map(Movimentacao::toString).collect(Collectors.toUnmodifiableList());
  }



  @Override
  public void incluirDependente(long numeroConta, DadosContaDto dadosContaDto) {
    ContaCorrentePoupanca contaCorrentePoupanca = contaRepository.obter(numeroConta);
    if (TipoConta.CORRENTE.equals(contaCorrentePoupanca.getTipoConta())) {
      if (nonNull(dadosContaDto.getDependente())) {
        contaCorrentePoupanca.incluirDependente(dadosContaDto.getDependente());
      }
    }
  }
}
