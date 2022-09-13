package com.example.demo.domain.conta;

import com.example.demo.domain.movimentacao.TipoMovimentacao;
import com.example.demo.domain.movimentacao.evento.MovimentoRealizado;

public class DepositoRealizadoEvent extends MovimentoRealizado {

    public DepositoRealizadoEvent(long numeroConta, long agencia, double valorDepositado, double saldoAposDeposito) {
        super(numeroConta, agencia, valorDepositado, saldoAposDeposito);
    }

    @Override
    public TipoMovimentacao getTipoMovimentacao() {
        return TipoMovimentacao.DEPOSITO;
    }
}
