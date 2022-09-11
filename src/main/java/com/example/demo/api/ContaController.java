package com.example.demo.api;

import com.example.demo.api.dto.AberturaContaDto;
import com.example.demo.application.conta.AberturaContaService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContaController {

    private AberturaContaService aberturaContaContaCorrenteService;
    private AberturaContaService aberturaContaPoupancaService;
    private AberturaContaService aberturaContaInvestimentoService;

    public ContaController(
            @Qualifier(value = "Corrente") AberturaContaService aberturaContaContaCorrenteService,
            @Qualifier(value = "Poupanca") AberturaContaService aberturaContaPoupancaService,
            @Qualifier(value = "Investimento") AberturaContaService aberturaContaInvestimentoService
    ) {
        this.aberturaContaContaCorrenteService = aberturaContaContaCorrenteService;
        this.aberturaContaPoupancaService = aberturaContaPoupancaService;
        this.aberturaContaInvestimentoService = aberturaContaInvestimentoService;
    }

    @PostMapping(value = "/v1/conta-corrente")
    public ResponseEntity<Long> abrirContaCorrente(@RequestBody AberturaContaDto aberturaContaDto) {
        final long numeroConta = aberturaContaContaCorrenteService.abrir(aberturaContaDto);
        return ResponseEntity.ok().body(numeroConta);
    }

    @PostMapping(value = "/v1/conta-poupanca")
    public ResponseEntity<Long> abrirContaPoupanca(@RequestBody AberturaContaDto aberturaContaDto) {
        final long numeroConta = aberturaContaPoupancaService.abrir(aberturaContaDto);
        return ResponseEntity.ok().body(numeroConta);
    }

    @PostMapping(value = "/v1/conta-investimento")
    public ResponseEntity<Long> abrirContaInvestimento(@RequestBody AberturaContaDto aberturaContaDto) {
        final long numeroConta = aberturaContaInvestimentoService.abrir(aberturaContaDto);
        return ResponseEntity.ok().body(numeroConta);
    }


}
