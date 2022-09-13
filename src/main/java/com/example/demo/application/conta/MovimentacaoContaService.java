package com.example.demo.application.conta;

import com.example.demo.api.dto.MovimentacaoDto;

import java.util.List;

public interface MovimentacaoContaService {
    List<String> gerarExtrato(final long numeroConta);

    void depositar(final long numeroConta, final MovimentacaoDto movimentacaoDto);
}
