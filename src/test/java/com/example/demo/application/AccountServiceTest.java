package com.example.demo.application;

import com.example.demo.api.dto.AccountOpeningDto;
import com.example.demo.domain.Account;
import com.example.demo.domain.AccountType;
import com.example.demo.infrastructure.AccountRepository;
import com.example.demo.infrastructure.external.CustomerAccountNumberGenerator;
import com.example.demo.infrastructure.external.CostumerInvestmentAccountNumberGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private CostumerInvestmentAccountNumberGenerator costumerInvestmentAccountNumberGenerator;
    @Mock
    private CustomerAccountNumberGenerator customerAccountNumberGenerator;

    @InjectMocks
    private AccountService accountService;

    /*@Test
    @DisplayName("Deve abrir conta corrente")
    void deveAbrirContaCorrente() {
        var contaEsperada = new Account("90909090", AccountType.CHECKING, 123, 1L);

        doReturn(contaEsperada).when(accountRepository).save(contaEsperada);
        doReturn(123L).when(customerAccountNumberGenerator).gerar();

        var dadosAberturaConta = new AccountOpeningDto(1L, "90909090");
        long numeroContaCriada = accountService.abrirConta(dadosAberturaConta, AccountType.CHECKING);

        Assertions.assertEquals(123, numeroContaCriada);

    }*/
}