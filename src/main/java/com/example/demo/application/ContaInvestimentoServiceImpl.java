package com.example.demo.application;

import com.example.demo.api.DadosContaDto;
import com.example.demo.api.MovimentacaoContaDto;
import com.example.demo.domain.ContaCorrentePoupanca;
import com.example.demo.domain.ContaInvestimento;
import com.example.demo.domain.Movimentacao;
import com.example.demo.domain.TipoConta;
import com.example.demo.domain.TipoRestricao;
import com.example.demo.infrastructure.MovimentacaoRepository;
import com.example.demo.infrastructure.external.GeradorNumeroContaInvestimento;
import com.example.demo.infrastructure.repository.ContaRepository;
import com.example.demo.infrastructure.repository.RestricaoRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ContaInvestimentoServiceImpl implements ContaService {

  private ContaRepository contaRepository;
  private GeradorNumeroContaInvestimento geradorNumeroContaInvestimento;
  private MovimentacaoRepository movimentacaoRepository;

  public ContaInvestimentoServiceImpl(ContaRepository contaRepository,
      GeradorNumeroContaInvestimento geradorNumeroContaInvestimento,
      RestricaoRepository restricaoRepository, MovimentacaoRepository movimentacaoRepository) {
    this.contaRepository = contaRepository;
    this.geradorNumeroContaInvestimento = geradorNumeroContaInvestimento;
    this.movimentacaoRepository = movimentacaoRepository;
  }

  public long abrirConta(final DadosContaDto dadosContaDto,
      final TipoConta tipoConta) {
    long numeroContaInvestimento = geradorNumeroContaInvestimento.gerar(
        dadosContaDto.getCpfTitular());
    ContaInvestimento contaInvestimento = new ContaInvestimento(
        dadosContaDto.getCpfTitular(),
        numeroContaInvestimento);
    ContaCorrentePoupanca conta = contaRepository.salvar(
        contaInvestimento);

    return conta.getNumero();
  }

  @Override
  public void movimentar(long numeroConta, MovimentacaoContaDto movimentacaoContaDto) {
    throw new OperacaoInvalidaException(
        "Não é possível fazer movimentação de SAQUE/DEPÓsito para conta de investimento");
  }

  @Override
  public void incluirDependente(long numeroConta, DadosContaDto dadosContaDto) {
    throw new OperacaoInvalidaException(
        "Não é possível incluir dependente para conta de investimento");
  }

  public List<String> gerarExtrato(long numeroConta) {
    return this.movimentacaoRepository.findByNumeroConta(numeroConta).stream()
        .map(Movimentacao::toString).collect(Collectors.toUnmodifiableList());
  }

  @Override
  public List<TipoRestricao> listarRestricoes(String cpf) {
    return null;
  }
}
