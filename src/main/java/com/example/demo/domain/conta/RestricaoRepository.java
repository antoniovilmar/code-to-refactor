package com.example.demo.domain.conta;

import java.util.List;

public interface RestricaoRepository {

  boolean temRestricao(String cpf, TipoRestricao tipoRestricao);

  List<Restricao> listar(String cpf);

}
