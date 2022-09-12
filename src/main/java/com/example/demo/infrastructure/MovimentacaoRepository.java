package com.example.demo.infrastructure;

import com.example.demo.domain.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, UUID> {
    List<Movimentacao> findByNumeroConta(long numeroConta);
}
