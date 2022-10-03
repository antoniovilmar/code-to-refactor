package com.example.demo.application;

import static org.mockito.Mockito.doReturn;

import com.example.demo.api.DadosContaDto;
import com.example.demo.api.MovimentacaoContaDto;
import com.example.demo.domain.ContaCorrentePoupanca;
import com.example.demo.domain.TipoConta;
import com.example.demo.domain.TipoMovimentacao;
import com.example.demo.infrastructure.LimiteRepository;
import com.example.demo.infrastructure.MovimentacaoRepository;
import com.example.demo.infrastructure.external.ConsultaPerfilRisco;
import com.example.demo.infrastructure.external.GeradorNumeroConta;
import com.example.demo.infrastructure.external.GeradorNumeroContaInvestimento;
import com.example.demo.infrastructure.repository.ContaRepository;
import com.example.demo.infrastructure.repository.LimiteRepository;
import com.example.demo.infrastructure.repository.RestricaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ContaCorrentePoupancaServiceTest {

  @Mock
  private ContaRepository contaRepository;
  //  @Mock
//  private ContaInvestimentoRepository contaInvestimentoRepository;
  @Mock
  private GeradorNumeroConta geradorNumeroConta;
  @Mock
  private GeradorNumeroContaInvestimento geradorNumeroContaInvestimento;
  @Mock
  private RestricaoRepository restricaoRepository;
  @Mock
  private MovimentacaoRepository movimentacaoRepository;
  @Mock
  private ConsultaPerfilRisco consultaPerfilRisco;
  @Mock
  private LimiteRepository limiteRepository;
  @InjectMocks
  private ContaServiceImpl contaServiceImpl;

  @Test
  @DisplayName("Deve abrir conta corrente")
  void deveAbrirContaCorrente() {
    var contaEsperada = new ContaCorrentePoupanca(validacaoAberturaContaCorrente, "90909090", TipoConta.CORRENTE, 123, 1L);
    doReturn(contaEsperada).when(contaRepository).save(contaEsperada);
    doReturn(123L).when(geradorNumeroConta).gerar(TipoConta.CORRENTE);

    var dadosAberturaConta = new DadosContaDto(1L, "90909090", "1293912039");
    long numeroContaCriada = contaServiceImpl.abrirConta(dadosAberturaConta, TipoConta.CORRENTE);

    Assertions.assertEquals(123, numeroContaCriada);

//    this.limiteRepository;
//    this.consultaPerfilRisco;
//    this.movimentacaoRepository;
//    this.restricaoRepository;
//    this.geradorNumeroContaInvestimento;
  }

  @Test
  @DisplayName("Deve abrir conta corrente")
  void deveAbrirContaCorrenteDigital() {
    var contaEsperada = new ContaCorrentePoupanca(validacaoAberturaContaCorrente, "90909090", TipoConta.INVESTIMENTO, 123, 1L);
    var movimentacaoContaDto = new MovimentacaoContaDto(100, TipoMovimentacao.SAQUE);
    doReturn(contaEsperada).when(contaRepository).getById(123L);
    doReturn(123L).when(geradorNumeroConta).gerar(TipoConta.CORRENTE);

    contaServiceImpl.movimentar(123L, movimentacaoContaDto);

  }


}