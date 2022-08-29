package com.example.demo.api;

import com.example.demo.api.dto.AccountOpeningDto;
import com.example.demo.application.AccountService;
import com.example.demo.domain.AccountType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/v1/conta-corrente")
    public ResponseEntity<Long> openCheckingAccount(@RequestBody AccountOpeningDto accountOpeningDto) {
        accountOpeningDto.setAccountType(AccountType.CHECKING);
        return ResponseEntity.ok().body(accountService.openAccount(accountOpeningDto));
    }

    @PostMapping(value = "/v1/conta-investimento")
    public ResponseEntity<Long> abrirContaInvestimento(@RequestBody AccountOpeningDto accountOpeningDto) {
        accountOpeningDto.setAccountType(AccountType.INVESTMENT);
        return ResponseEntity.ok().body(accountService.openAccount(accountOpeningDto));
    }

    @PostMapping(value = "/v1/conta-poupanca")
    public ResponseEntity<Long> abrirContaPoupanca(@RequestBody AccountOpeningDto accountOpeningDto) {
        accountOpeningDto.setAccountType(AccountType.SAVINGS);
        return ResponseEntity.ok().body(accountService.openAccount(accountOpeningDto));
    }
}
