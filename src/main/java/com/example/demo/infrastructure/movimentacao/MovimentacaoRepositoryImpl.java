package com.example.demo.infrastructure.movimentacao;

import com.example.demo.domain.movimentacao.Movimentacao;
import com.example.demo.domain.movimentacao.MovimentacaoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovimentacaoRepositoryImpl implements MovimentacaoRepository {

    private MovimentacaoSpringData data;

    public MovimentacaoRepositoryImpl(MovimentacaoSpringData data) {
        this.data = data;
    }

    @Override
    public Movimentacao salvar(Movimentacao movimentacao) {
        return this.data.save(movimentacao);
    }

    @Override
    public List<Movimentacao> listar(long numeroConta) {
        return this.data.findByNumeroConta(numeroConta);
    }
}
