package com.example.demo.domain.impl;

import com.example.demo.domain.*;
import com.example.demo.domain.util.AccountUtil;
import java.util.Objects;

public class ContaInvestimento extends Conta implements Deposita {

    public ContaInvestimento(long number, long agency, String holderCPF) {
        super(number, agency, holderCPF, TipoConta.INVESTIMENTO);
    }

    @Override
    public void depositar(final double valor) {
        setBalance(getBalance() + valor);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ContaInvestimento) {
            ContaInvestimento accountToCompare = (ContaInvestimento) o;
            ContaInvestimento thisAccount =
                    new ContaInvestimento(getNumero(), getAgencia(), getCpfTitular());

            return AccountUtil.isTheSameFields(accountToCompare, thisAccount);
        }
        return false;

    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumero(), getAgencia(), getCpfTitular());
    }

}