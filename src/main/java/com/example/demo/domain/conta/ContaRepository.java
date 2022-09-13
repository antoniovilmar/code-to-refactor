package com.example.demo.domain.conta;

public interface ContaRepository<C extends Conta> {
    C obter(long numeroConta);

    C salvar(C conta);
}
