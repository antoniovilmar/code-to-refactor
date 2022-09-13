package com.example.demo.infrastructure.external;

import org.springframework.stereotype.Service;

@Service
public class GeradorNumeroContaInvestimentoRandomicoImpl implements GeradorNumeroContaInvestimento {
    @Override
    public long gerar(String cpf) {
        {
            final long primeiroDigitoCpf = Long.parseLong(cpf.substring(0, 1));
            return primeiroDigitoCpf + (long) (100000l + Math.random() * 999999l);
        }
    }
}
