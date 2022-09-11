package com.example.demo.api;

import com.example.demo.application.ContaService;
import com.example.demo.domain.TipoConta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContaController {
    private ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping(value = "/v1/conta-corrente")
    public ResponseEntity abrirContaCorrente(@RequestBody DadosAberturaContaDto dadosAberturaContaDto) {
        long numeroConta = this.contaService.abrirConta(dadosAberturaContaDto, TipoConta.CORRENTE);
        return ResponseEntity.created(null).body(numeroConta);

    }

    @PostMapping(value = "/v1/conta-investimento")
    public ResponseEntity abrirContaInvestimento(@RequestBody DadosAberturaContaDto dadosAberturaContaDto) {
        long numeroConta = this.contaService.abrirConta(dadosAberturaContaDto, TipoConta.INVESTIMENTO);
        return ResponseEntity.created(null).body(numeroConta);
    }

    @PostMapping(value = "/v1/conta-poupanca")
    public ResponseEntity abrirContaPoupanca(@RequestBody DadosAberturaContaDto dadosAberturaContaDto) {
        long numeroConta = this.contaService.abrirConta(dadosAberturaContaDto, TipoConta.POUPANCA);
        return ResponseEntity.created(null).body(numeroConta);

    }

    @PostMapping(value = "/v1/conta/{numeroConta}/movimentacao")
    public ResponseEntity movimentar(@PathVariable long numeroConta, @RequestBody MovimentacaoContaDto movimentacaoContaDto) {
        this.contaService.movimentar(numeroConta, movimentacaoContaDto);
        return ResponseEntity.created(null).build();
    }

    @GetMapping(value = "/v1/conta-extrato/{numeroConta}")
    public ResponseEntity gerarExtrato(@PathVariable long numeroConta) {
        final List<String> extrato = this.contaService.gerarExtrato(numeroConta);
        return ResponseEntity.ok().body(extrato);

    }
}
