package com.example.demo.infrastructure.external;

import com.example.demo.domain.TipoConta;
import org.springframework.stereotype.Service;

@Service
public class MockGeradorNumeroContaImpl implements GeradorNumeroConta {

    @Override
    public long gerar(TipoConta tipoConta) {
        return (long) (100000l + Math.random() * 999999l);
    }
}
