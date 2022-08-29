package com.example.demo.domain.util;

import com.example.demo.domain.Account;

import java.util.Objects;

public class AccountUtil {
    public static boolean isTheSameFields(Account accountToCompare, Account thisAccount){
        return Objects.equals(accountToCompare.getNumber(),thisAccount.getNumber())
                && Objects.equals(accountToCompare.getAgency(),thisAccount.getAgency())
                && Objects.equals(accountToCompare.getHolderCPF(), thisAccount.getHolderCPF());
    }
}
