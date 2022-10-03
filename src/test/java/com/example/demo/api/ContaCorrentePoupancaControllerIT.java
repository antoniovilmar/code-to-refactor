package com.example.demo.api;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.domain.ContaCorrentePoupanca;
import com.example.demo.domain.Movimentacao;
import com.example.demo.domain.TipoConta;
import com.example.demo.domain.TipoMovimentacao;
import com.example.demo.infrastructure.MovimentacaoRepository;
import com.example.demo.infrastructure.repository.ContaRepository;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ContaCorrentePoupancaControllerIT extends BaseIT {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private MovimentacaoRepository movimentacaoRepository;

  @Autowired
  private ContaRepository contaRepository;

  @AfterEach
  void tearDown() {
//    this.contaRepository.deleteAll();
    this.movimentacaoRepository.deleteAll();
  }

  @Test
  @DisplayName("Deve abrir uma conta corrente")
  void deveAbrirContaCorrente() throws Exception {

    final DadosContaDto dadosContaDto = new DadosContaDto(1, "99999",
        "1293912039");

    String numeroConta = mvc
        .perform(
            post("/v1/conta-corrente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(dadosContaDto)))
        .andExpect(status().isCreated())
        .andReturn()
        .getResponse().getContentAsString();

    assertNotNull(numeroConta);

  }

  @Test
  @DisplayName("Deve abrir uma conta poupança")
  void deveAbrirContaPoupanca() throws Exception {

    final DadosContaDto dadosContaDto = new DadosContaDto(1, "99999",
        "1293912039");

    String numeroConta = mvc
        .perform(
            post("/v1/conta-poupanca")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(dadosContaDto)))
        .andExpect(status().isCreated())
        .andReturn()
        .getResponse().getContentAsString();

    assertNotNull(numeroConta);

  }

  @Test
  @DisplayName("Deve abrir uma conta investimento")
  void deveAbrirContaInvestimento() throws Exception {

    final DadosContaDto dadosContaDto = new DadosContaDto(1, "99999",
        "1293912039");

    String numeroConta = mvc
        .perform(
            post("/v1/conta-investimento")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(dadosContaDto)))
        .andExpect(status().isCreated())
        .andReturn()
        .getResponse().getContentAsString();

    assertNotNull(numeroConta);

  }

  @Test
  @DisplayName("Quando é depositado 100 reais o saldo deve totalizar 100 reais na conta 1234")
  void deveFazerUmDeposito() throws Exception {

    final ContaCorrentePoupanca contaCorrentePoupanca = new ContaCorrentePoupanca(
        validacaoAberturaContaCorrente, "99999", TipoConta.CORRENTE, 1234, 1);
//    this.contaRepository.save(contaCorrentePoupanca);

    final MovimentacaoContaDto movimentacaoContaDto = new MovimentacaoContaDto(100,
        TipoMovimentacao.DEPOSITO);

    mvc.perform(
            post("/v1/conta/{numeroConta}/movimentacao", contaCorrentePoupanca.getNumero())
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(movimentacaoContaDto)))
        .andExpect(status().isCreated());

    List<Movimentacao> movimentacoes = this.movimentacaoRepository.findAll();

    assertAll("Comparando dados da movimentação de depósito",
        () -> assertEquals(TipoMovimentacao.DEPOSITO, movimentacoes.get(0).getMovimentacao()),
        () -> assertEquals(100, movimentacoes.get(0).getValorMovimentado()),
        () -> assertEquals(100, movimentacoes.get(0).getSaldoAposMovimento()),
        () -> assertEquals(contaCorrentePoupanca.getNumero(), movimentacoes.get(0).getNumeroConta()),
        () -> assertEquals(contaCorrentePoupanca.getTipoConta(), movimentacoes.get(0).getTipoConta()),
        () -> assertEquals(contaCorrentePoupanca.getAgencia(), movimentacoes.get(0).getAgencia()),
        () -> assertEquals(LocalDate.now(), movimentacoes.get(0).getData().toLocalDate()),
        () -> assertTrue(movimentacoes.size() == 1),
        () -> assertNotNull(movimentacoes.get(0).getId())
    );

  }

  @Test
  @DisplayName("Quando é sacado 100 reais o saldo deve totalizar -100 reais na conta 1234")
  void deveFazerUmSaque() throws Exception {

    final ContaCorrentePoupanca contaCorrentePoupanca = new ContaCorrentePoupanca(
        validacaoAberturaContaCorrente, "99999", TipoConta.CORRENTE, 1234, 1);
//    this.contaRepository.save(contaCorrentePoupanca);

    final MovimentacaoContaDto movimentacaoContaDto = new MovimentacaoContaDto(100,
        TipoMovimentacao.SAQUE);

    mvc.perform(
            post("/v1/conta/{numeroConta}/movimentacao", contaCorrentePoupanca.getNumero())
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(movimentacaoContaDto)))
        .andExpect(status().isCreated());

    List<Movimentacao> movimentacoes = this.movimentacaoRepository.findAll();

    assertAll("Comparando dados da movimentação de saque",
        () -> assertEquals(TipoMovimentacao.SAQUE, movimentacoes.get(0).getMovimentacao()),
        () -> assertEquals(100, movimentacoes.get(0).getValorMovimentado()),
        () -> assertEquals(-100, movimentacoes.get(0).getSaldoAposMovimento()),
        () -> assertEquals(contaCorrentePoupanca.getNumero(), movimentacoes.get(0).getNumeroConta()),
        () -> assertEquals(contaCorrentePoupanca.getTipoConta(), movimentacoes.get(0).getTipoConta()),
        () -> assertEquals(contaCorrentePoupanca.getAgencia(), movimentacoes.get(0).getAgencia()),
        () -> assertEquals(LocalDate.now(), movimentacoes.get(0).getData().toLocalDate()),
        () -> assertTrue(movimentacoes.size() == 1),
        () -> assertNotNull(movimentacoes.get(0).getId())
    );

  }

  @Test
  @DisplayName("Quando é gerado o extrato deve ter uma movimentação de depósito de 100 reais e um saque de 50 reais na conta 1234")
  void deveGerarExtrato() throws Exception {

    final ContaCorrentePoupanca contaCorrentePoupanca = new ContaCorrentePoupanca(
        validacaoAberturaContaCorrente, "99999", TipoConta.CORRENTE, 1234, 1);
    final Movimentacao movimentacaoSaque = new Movimentacao(1234, 1, -50, 50, TipoConta.CORRENTE,
        TipoMovimentacao.SAQUE);
    final Movimentacao movimentacaoDeposito = new Movimentacao(1234, 1, 50, 100, TipoConta.CORRENTE,
        TipoMovimentacao.DEPOSITO);
//    this.contaRepository.save(contaCorrentePoupanca);
    this.movimentacaoRepository.saveAll(List.of(movimentacaoSaque, movimentacaoDeposito));

    mvc.perform(
            get("/v1/conta-extrato/{numeroConta}", contaCorrentePoupanca.getNumero())
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$.[*]",
            hasItems(movimentacaoSaque.toString(), movimentacaoDeposito.toString())))
        .andExpect(status().isOk());

  }

}
