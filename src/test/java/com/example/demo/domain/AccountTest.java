package com.example.demo.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountTest {

    /*@Test
    @DisplayName("Deve permitir saque, quando é conta corrente")
    void devePermitirSaqueQuandoParaContaCorrente() {
        Account account = new Account("99999999", AccountType.CORRENTE, 123L, 1L);
        account.withdraw(10);

        Assertions.assertEquals(-10, account.getBalance());
    }

    @Test
    @DisplayName("Deve permitir saque para conta poupanca")
    void devePermitirSaqueParaContaPoupanca() {
        Account account = new Account("99999999", AccountType.POUPANCA, 123L, 1L);
        account.deposit(100);
        account.withdraw(10);

        Assertions.assertEquals(90, account.getBalance());
    }

    @Test
    @DisplayName("Não deve permitir efetuar o saque, quando é conta investimento")
    void naoDevePermitirSaqueParaContaInvestimento() {
        Account account = new Account("99999999", AccountType.INVESTIMENTO, 123L, 1L);

        assertThrows(
                DomainBusinessException.class,
                () -> account.withdraw(10)
        );
    }

    @Test
    @DisplayName("Não deve permitir a inclusão de dependente para conta de investimento")
    void naoDevePermitirAInclusaoDeDependenteParaContaInvestimento() {
        Account account = new Account("99999999", AccountType.INVESTIMENTO, 123L, 1L);

        assertThrows(
                DomainBusinessException.class,
                () -> account.includeDependent(new Dependent("1234", "51888888"))
        );
    }

    @Test
    @DisplayName("Não deve permitir a inclusão de dependente para conta poupança")
    void naoDevePermitirAInclusaoDeDependenteParaContaPoupanca() {
        Account account = new Account("99999999", AccountType.POUPANCA, 123L, 1L);

        assertThrows(
                DomainBusinessException.class,
                () -> account.includeDependent(new Dependent("1234", "51888888"))
        );
    }

    @Test
    @DisplayName("Deve incluir dependente para conta corrente")
    void deveInclusaoDependenteParaContaCorrente() {
        Account account = new Account("99999999", AccountType.CORRENTE, 123L, 1L);
        account.includeDependent(new Dependent("1234", "51888888"));
    }

    @Test
    @DisplayName("Não deve permitir saque, quando ultrapassa o cheque especial de mil reais para conta corrente")
    void naoDevePermitirSaqueQuandoUltrapassaChequeEspecialParaContaCorrente() {
        Account account = new Account("99999999", AccountType.CORRENTE, 123L, 1L);

        assertThrows(
                DomainBusinessException.class,
                () -> account.withdraw(1001)
        );
    }

    @Test
    @DisplayName("Não deve permitir saque, quando saldo fica negativo para conta poupanca")
    void naoDevePermitirSaqueQuandoSaldoFicaNegativoParaContaPoupanca() {
        Account account = new Account("99999999", AccountType.POUPANCA, 123L, 1L);

        assertThrows(
                DomainBusinessException.class,
                () -> account.withdraw(1)
        );
    }*/

}