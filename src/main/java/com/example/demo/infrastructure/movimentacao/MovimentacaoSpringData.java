package com.example.demo.infrastructure.movimentacao;

import com.example.demo.domain.conta.Conta;
import com.example.demo.domain.movimentacao.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface MovimentacaoSpringData extends JpaRepository<Movimentacao, Long> {
    List<Movimentacao> findByNumeroConta(long numeroConta);
}