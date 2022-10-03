package com.example.demo.application;

public class OperacaoInvalidaException extends RuntimeException {

    public OperacaoInvalidaException(String mensagem) {
        super(mensagem);
    }
}
