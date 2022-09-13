package com.example.demo.domain.conta;

import com.example.demo.domain.movimentacao.TipoMovimentacao;
import com.example.demo.domain.movimentacao.evento.MovimentoRealizado;

public class SaqueRealizadoEvent extends MovimentoRealizado {

    public SaqueRealizadoEvent(long numeroConta, long agencia, double valorSacado, double saldoAposSaque) {
        super(numeroConta, agencia, valorSacado, saldoAposSaque);
    }

    @Override
    public TipoMovimentacao getTipoMovimentacao() {
        return TipoMovimentacao.SAQUE;
    }
}
