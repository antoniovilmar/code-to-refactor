package com.example.demo.api.dto;

import com.example.demo.domain.AccountType;

public class AccountOpeningDto {
    private long agency;
    private String holderCPF;
    private AccountType accountType;


    public AccountOpeningDto(long agency, String holderCPF) {
        this.agency = agency;
        this.holderCPF = holderCPF;
    }

    public long getAgency() {
        return agency;
    }

    public String getHolderCPF() {
        return holderCPF;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}
