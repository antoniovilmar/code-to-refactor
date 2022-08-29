package com.example.demo.domain.exception;

public class DomainBusinessException extends RuntimeException {

    public DomainBusinessException(String message) {
        super(message);
    }
}
