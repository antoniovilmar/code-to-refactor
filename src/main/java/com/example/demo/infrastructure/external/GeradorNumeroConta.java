package com.example.demo.infrastructure.external;

import com.example.demo.domain.TipoConta;
public interface GeradorNumeroConta {

    long gerar(TipoConta tipoConta);
}
