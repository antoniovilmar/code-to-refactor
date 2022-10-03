package com.example.demo.infrastructure.repository.restricao;

import com.example.demo.domain.conta.Restricao;
import com.example.demo.domain.conta.TipoRestricao;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class RestricaoRepository implements com.example.demo.domain.conta.RestricaoRepository {

  private RestricaoSpringData data;

  public RestricaoRepository(RestricaoSpringData data) {
    this.data = data;
  }

  public boolean temRestricao(String cpf, TipoRestricao tipoRestricao) {
    return this.data.existsByCpfAndTipoRestricao(cpf, tipoRestricao);
  }

  public List<Restricao> listar(String cpf) {
    return this.data.findAllByCpf(cpf);
  }
}
