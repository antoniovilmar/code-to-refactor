package com.example.demo.domain.util;

import com.example.demo.domain.Conta;

import java.util.Objects;

public class AccountUtil {
    public static boolean isTheSameFields(Conta contaToCompare, Conta thisConta){
        return Objects.equals(contaToCompare.getNumber(), thisConta.getNumber())
                && Objects.equals(contaToCompare.getAgency(), thisConta.getAgency())
                && Objects.equals(contaToCompare.getHolderCPF(), thisConta.getHolderCPF());
    }
}
