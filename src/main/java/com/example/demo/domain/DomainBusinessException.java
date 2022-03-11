package com.example.demo.domain;

public class DomainBusinessException extends RuntimeException {

    public DomainBusinessException(String mensagem) {
        super(mensagem);
    }
}
