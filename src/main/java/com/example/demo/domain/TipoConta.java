package com.example.demo.domain;

public enum TipoConta {
    CORRENTE,
    POUPANCA,
    INVESTIMENTO;

    public boolean isCorrente() {
        return CORRENTE.equals(this);
    }


}
