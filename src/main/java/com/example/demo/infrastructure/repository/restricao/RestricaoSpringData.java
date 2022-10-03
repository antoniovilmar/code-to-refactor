package com.example.demo.infrastructure.repository.restricao;

import com.example.demo.domain.conta.Restricao;
import com.example.demo.domain.conta.TipoRestricao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

interface RestricaoSpringData extends JpaRepository<Restricao, Long> {

  boolean existsByCpfAndTipoRestricao(String cpf, TipoRestricao tipoRestricao);

  List<Restricao> findAllByCpf(String cpf);
}