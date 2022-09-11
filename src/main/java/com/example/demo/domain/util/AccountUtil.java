package com.example.demo.domain.util;

import com.example.demo.domain.conta.Conta;

import java.util.Objects;

public class AccountUtil {
    private AccountUtil() {}

    public static boolean isTheSameFields(Conta contaToCompare, Conta thisConta){
        return Objects.equals(contaToCompare.getNumero(), thisConta.getNumero())
                && Objects.equals(contaToCompare.getAgencia(), thisConta.getAgencia())
                && Objects.equals(contaToCompare.getCpfTitular(), thisConta.getCpfTitular());
    }
}
