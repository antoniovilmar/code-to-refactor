package com.example.demo.domain.conta;

import com.example.demo.domain.TipoRestricao;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Restricao {

  @Id
  private UUID id;
  private String cpf;
  private TipoRestricao tipoRestricao;

  public Restricao(UUID id, String cpf, TipoRestricao tipoRestricao) {
    this.id = id;
    this.cpf = cpf;
    this.tipoRestricao = tipoRestricao;
  }

  public UUID getId() {
    return id;
  }

  public String getCpf() {
    return cpf;
  }

  public TipoRestricao getTipoRestricao() {
    return tipoRestricao;
  }
}
