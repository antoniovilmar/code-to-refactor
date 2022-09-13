package com.example.demo.domain.movimentacao;

import java.util.List;

public interface MovimentacaoRepository {
    Movimentacao salvar(Movimentacao movimentacao);

    List<Movimentacao> listar(long numeroConta);
}
