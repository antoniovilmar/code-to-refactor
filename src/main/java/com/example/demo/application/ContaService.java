package com.example.demo.application;

import com.example.demo.api.DadosContaDto;
import com.example.demo.api.MovimentacaoContaDto;
import com.example.demo.domain.TipoConta;
import com.example.demo.domain.TipoRestricao;
import java.util.List;

public interface ContaService {

  long abrirConta(DadosContaDto dadosContaDto, TipoConta tipoConta);

  void movimentar(long numeroConta, MovimentacaoContaDto movimentacaoContaDto);

  List<String> gerarExtrato(long numeroConta);

  List<TipoRestricao> listarRestricoes(String cpf);

  void incluirDependente(long numeroConta, DadosContaDto dadosContaDto);

}
