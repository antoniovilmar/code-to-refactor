package com.example.demo.domain.movimentacao.evento;

import com.example.demo.domain.movimentacao.Movimentacao;
import com.example.demo.domain.movimentacao.MovimentacaoRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MovimentacaoRealizadaEventListener {

    private MovimentacaoRepository movimentacaoRepository;

    public MovimentacaoRealizadaEventListener(MovimentacaoRepository movimentacaoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
    }

    @EventListener
    public void listener(final MovimentoRealizado event) {
        final Movimentacao movimentacao = new Movimentacao(event.getNumeroConta(), event.getAgencia(), event.getSaldoAposMovimento(), event.getValorMovimentado(), event.getTipoMovimentacao());
        this.movimentacaoRepository.salvar(movimentacao);
    }
}
