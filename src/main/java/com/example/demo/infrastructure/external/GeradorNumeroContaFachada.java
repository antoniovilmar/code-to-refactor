package com.example.demo.infrastructure.external;

import com.example.demo.domain.TipoConta;
public interface GeradorNumeroContaFachada {

    long gerar(TipoConta tipoConta);
}
