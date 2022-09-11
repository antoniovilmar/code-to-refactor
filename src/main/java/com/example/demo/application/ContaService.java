package com.example.demo.application;

import com.example.demo.api.DadosAberturaContaDto;
import com.example.demo.api.MovimentacaoContaDto;
import com.example.demo.domain.Conta;
import com.example.demo.domain.Movimentacao;
import com.example.demo.domain.TipoConta;
import com.example.demo.domain.TipoMovimentacao;
import com.example.demo.infrastructure.ContaRepository;
import com.example.demo.infrastructure.MovimentacaoRepository;
import com.example.demo.infrastructure.external.GeradorNumeroContaFachada;
import com.example.demo.infrastructure.external.GeradorNumeroContaInvestimentoFachada;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContaService {

    private ContaRepository contaRepository;
    private GeradorNumeroContaFachada geradorNumeroContaFachada;
    private GeradorNumeroContaInvestimentoFachada geradorNumeroContaInvestimentoFachada;

    private MovimentacaoRepository movimentacaoRepository;

    public ContaService(ContaRepository contaRepository, GeradorNumeroContaFachada geradorNumeroContaFachada, GeradorNumeroContaInvestimentoFachada geradorNumeroContaInvestimentoFachada, MovimentacaoRepository movimentacaoRepository) {
        this.contaRepository = contaRepository;
        this.geradorNumeroContaFachada = geradorNumeroContaFachada;
        this.geradorNumeroContaInvestimentoFachada = geradorNumeroContaInvestimentoFachada;
        this.movimentacaoRepository = movimentacaoRepository;
    }

    public long abrirConta(final DadosAberturaContaDto dadosAberturaContaDto, final TipoConta tipoConta) {
        if (TipoConta.INVESTIMENTO.equals(tipoConta)) {
            long numeroContaInvestimento = geradorNumeroContaInvestimentoFachada.gerar(dadosAberturaContaDto.getCpfTitular());
            Conta conta = new Conta(dadosAberturaContaDto.getCpfTitular(), tipoConta, numeroContaInvestimento, dadosAberturaContaDto.getAgencia());
            contaRepository.save(conta);
        }
        long numeroConta = geradorNumeroContaFachada.gerar(tipoConta);
        var contaParaCriar = new Conta(dadosAberturaContaDto.getCpfTitular(), tipoConta, numeroConta, dadosAberturaContaDto.getAgencia());
        Conta contaCriada = contaRepository.save(contaParaCriar);

        return contaCriada.getNumero();
    }

    public void movimentar(final long numeroConta, MovimentacaoContaDto movimentacaoContaDto) {
        Conta conta = contaRepository.getById(numeroConta);
        if (TipoMovimentacao.SAQUE.equals(movimentacaoContaDto.getMovimento())) {
            conta.sacar(movimentacaoContaDto.getValor());
        } else {
            conta.depositar(movimentacaoContaDto.getValor());
        }

        contaRepository.save(conta);
        Movimentacao movimentacao = new Movimentacao(conta.getNumero(), conta.getAgencia(), conta.getSaldo(), movimentacaoContaDto.getValor(), conta.getTipoConta(), movimentacaoContaDto.getMovimento());
        movimentacaoRepository.save(movimentacao);

    }

    public List<String> gerarExtrato(long numeroConta) {
        return this.movimentacaoRepository.findByNumeroConta(numeroConta).stream().map(Movimentacao::toString).collect(Collectors.toUnmodifiableList());
    }
}
