package com.example.demo.api.dto;

import com.example.demo.domain.TipoConta;

public class AberturaContaDto {
    private long agency;
    private String holderCPF;
    private TipoConta tipoConta;


    public AberturaContaDto(long agency, String holderCPF) {
        this.agency = agency;
        this.holderCPF = holderCPF;
    }

    public long getAgency() {
        return agency;
    }

    public String getHolderCPF() {
        return holderCPF;
    }

    public TipoConta getAccountType() {
        return tipoConta;
    }

    public void setAccountType(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }
}
