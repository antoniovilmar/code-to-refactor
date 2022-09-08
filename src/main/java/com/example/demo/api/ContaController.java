package com.example.demo.api;

import com.example.demo.api.dto.AberturaContaDto;
import com.example.demo.application.ContaService;
import com.example.demo.domain.AccountType;
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
    public ResponseEntity<Long> openCheckingAccount(@RequestBody AberturaContaDto aberturaContaDto) {
        aberturaContaDto.setAccountType(AccountType.CHECKING);
        return ResponseEntity.ok().body(contaService.openAccount(aberturaContaDto));
    }

    @PostMapping(value = "/v1/conta-investimento")
    public ResponseEntity<Long> abrirContaInvestimento(@RequestBody AberturaContaDto aberturaContaDto) {
        aberturaContaDto.setAccountType(AccountType.INVESTMENT);
        return ResponseEntity.ok().body(contaService.openAccount(aberturaContaDto));
    }

    @PostMapping(value = "/v1/conta-poupanca")
    public ResponseEntity<Long> abrirContaPoupanca(@RequestBody AberturaContaDto aberturaContaDto) {
        aberturaContaDto.setAccountType(AccountType.SAVINGS);
        return ResponseEntity.ok().body(contaService.openAccount(aberturaContaDto));
    }
}
