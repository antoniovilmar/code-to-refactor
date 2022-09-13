package com.example.demo.api;

import com.example.demo.api.dto.MovimentacaoDto;
import com.example.demo.application.conta.MovimentacaoContaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContaMovimentacaoController {

    private MovimentacaoContaService movimentacaoContaService;

    public ContaMovimentacaoController(
            MovimentacaoContaService movimentacaoContaService) {
        this.movimentacaoContaService = movimentacaoContaService;
    }

    // /contas/numeroConta/deposito
       //contas/numeroconta/extratos
        // /contas-corrente
        // /contas-investimento


    @PostMapping(value = "/v1/contas/{numeroConta}/depositos")
    public ResponseEntity depositar(@PathVariable long numeroConta, @RequestBody MovimentacaoDto movimentacaoDto) {
        movimentacaoContaService.depositar(numeroConta, movimentacaoDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/v1/contas/{numeroConta}/extratos")
    public ResponseEntity gerarExrato(@PathVariable long numeroConta) {
        final List<String> extrato = movimentacaoContaService.gerarExtrato(numeroConta);
        return ResponseEntity.ok().body(extrato);
    }


}
