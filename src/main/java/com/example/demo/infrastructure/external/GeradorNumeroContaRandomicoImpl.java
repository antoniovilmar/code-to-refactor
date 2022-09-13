package com.example.demo.infrastructure.external;

import com.example.demo.domain.TipoConta;
import com.example.demo.domain.conta.Conta;
import org.springframework.stereotype.Service;

@Service
public class GeradorNumeroContaRandomicoImpl implements GeradorNumeroConta {
    @Override
    public long gerar(TipoConta tipoConta) {
        var multiplicador = tipoConta.isCorrente() ? 5 : 4;
        double numberLeft = Math.pow(10, multiplicador);
        double numberRight = numberLeft - 1;

        return (long) (numberLeft + Math.random() * numberRight);
    }
}
