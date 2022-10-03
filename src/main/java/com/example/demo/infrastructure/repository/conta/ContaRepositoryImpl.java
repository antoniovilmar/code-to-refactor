package com.example.demo.infrastructure.repository.conta;

import com.example.demo.domain.conta.Conta;
import com.example.demo.domain.conta.ContaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ContaRepositoryImpl implements ContaRepository<Conta> {
    private ContaSpringData data;

    public ContaRepositoryImpl(ContaSpringData data) {
        this.data = data;
    }

    @Override
    public Conta obter(long numeroConta) {
        return this.data.getById(numeroConta);
    }

    @Override
    public Conta salvar(Conta conta) {
        return this.data.save(conta);
    }
}
