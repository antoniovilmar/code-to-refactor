package com.example.demo.api;

import com.example.demo.application.ContaServiceImpl;
import com.example.demo.domain.TipoConta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContaController {
    private ContaServiceImpl contaServiceImpl;

    public ContaController(ContaServiceImpl contaServiceImpl) {
        this.contaServiceImpl = contaServiceImpl;
    }

    @PostMapping(value = "/v1/conta-corrente")
    public ResponseEntity abrirContaCorrente(@RequestBody DadosContaDto dadosContaDto) {
        long numeroConta = this.contaServiceImpl.abrirConta(dadosContaDto, TipoConta.CORRENTE);
        return ResponseEntity.created(null).body(numeroConta);

    }

    @PostMapping(value = "/v1/conta-investimento")
    public ResponseEntity abrirContaInvestimento(@RequestBody DadosContaDto dadosContaDto) {
        long numeroConta = this.contaServiceImpl.abrirConta(dadosContaDto, TipoConta.INVESTIMENTO);
        return ResponseEntity.created(null).body(numeroConta);
    }

    @PostMapping(value = "/v1/conta-poupanca")
    public ResponseEntity abrirContaPoupanca(@RequestBody DadosContaDto dadosContaDto) {
        long numeroConta = this.contaServiceImpl.abrirConta(dadosContaDto, TipoConta.POUPANCA);
        return ResponseEntity.created(null).body(numeroConta);

    }

    @PostMapping(value = "/v1/conta/{numeroConta}/movimentacao")
    public ResponseEntity movimentar(@PathVariable long numeroConta, @RequestBody MovimentacaoContaDto movimentacaoContaDto) {
        this.contaServiceImpl.movimentar(numeroConta, movimentacaoContaDto);
        return ResponseEntity.created(null).build();
    }

    @GetMapping(value = "/v1/conta-extrato/{numeroConta}")
    public ResponseEntity gerarExtrato(@PathVariable long numeroConta) {
        final List<String> extrato = this.contaServiceImpl.gerarExtrato(numeroConta);
        return ResponseEntity.ok().body(extrato);

    }
}
