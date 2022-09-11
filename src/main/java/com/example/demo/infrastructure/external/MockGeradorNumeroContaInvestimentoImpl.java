package com.example.demo.infrastructure.external;

import org.springframework.stereotype.Service;

@Service
public class MockGeradorNumeroContaInvestimentoImpl implements GeradorNumeroContaInvestimentoFachada {
    @Override
    public long gerar(String cpf) {
        {
            return (long) (100000l + Math.random() * 999999l);
        }
    }
}
