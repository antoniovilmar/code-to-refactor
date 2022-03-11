package com.example.demo.api;

import com.example.demo.application.ContaService;
import com.example.demo.domain.TipoConta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContaController {

    private ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping(value = "/v1/conta-corrente")
    public ResponseEntity abrirContaCorrente(@RequestBody DadosAberturaConta dadosAberturaConta) {
        long numeroConta = this.contaService.abrirConta(dadosAberturaConta, TipoConta.CORRENTE);
        return ResponseEntity.ok().body(numeroConta);

    }

    @PostMapping(value = "/v1/conta-investimento")
    public ResponseEntity abrirContaInvestimento(@RequestBody DadosAberturaConta dadosAberturaConta) {
        long numeroConta = this.contaService.abrirConta(dadosAberturaConta, TipoConta.INVESTIMENTO);
        return ResponseEntity.ok().body(numeroConta);
    }

    @PostMapping(value = "/v1/conta-poupanca")
    public ResponseEntity abrirContaPoupanca(@RequestBody DadosAberturaConta dadosAberturaConta) {
        long numeroConta = this.contaService.abrirConta(dadosAberturaConta, TipoConta.POUPANCA);
        return ResponseEntity.ok().body(numeroConta);

    }
}
