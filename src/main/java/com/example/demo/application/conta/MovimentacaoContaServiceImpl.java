package com.example.demo.application.conta;

import com.example.demo.api.dto.MovimentacaoDto;
import com.example.demo.domain.conta.Conta;
import com.example.demo.domain.conta.ContaRepository;
import com.example.demo.domain.movimentacao.Movimentacao;
import com.example.demo.domain.movimentacao.MovimentacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovimentacaoContaServiceImpl implements MovimentacaoContaService {
    private MovimentacaoRepository movimentacaoRepository;
    private ContaRepository contaRepository;

    public MovimentacaoContaServiceImpl(MovimentacaoRepository movimentacaoRepository, ContaRepository contaRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.contaRepository = contaRepository;
    }

    @Override
    public List<String> gerarExtrato(long numeroConta) {
        final List<Movimentacao> movimentacoes = movimentacaoRepository.listar(numeroConta);
        return converterMovimentacoesEmExtrato(movimentacoes);
    }

    @Override
    public void depositar(long numeroConta, MovimentacaoDto movimentacaoDto) {
        Conta conta = this.contaRepository.obter(numeroConta);
        conta.depositar(movimentacaoDto.getValor());
        this.contaRepository.salvar(conta);
    }

    private List<String> converterMovimentacoesEmExtrato(List<Movimentacao> movimentacoes) {
        return movimentacoes.stream().map(Movimentacao::toString).collect(Collectors.toList());
    }
}
