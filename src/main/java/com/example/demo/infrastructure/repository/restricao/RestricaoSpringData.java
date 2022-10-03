package com.example.demo.infrastructure;

import com.example.demo.domain.Restricao;
import com.example.demo.domain.TipoRestricao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

interface RestricaoSpringData extends JpaRepository<Restricao, Long> {
  boolean existsByCpfAndTipoRestricao(String cpf, TipoRestricao tipoRestricao);

  List<Restricao> findAllByCpf(String cpf);
}