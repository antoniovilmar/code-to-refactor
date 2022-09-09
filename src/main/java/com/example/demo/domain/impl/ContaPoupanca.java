package com.example.demo.domain.impl;

import com.example.demo.domain.Conta;
import com.example.demo.domain.Deposita;
import com.example.demo.domain.Saque;
import com.example.demo.domain.TipoConta;
import com.example.demo.domain.exception.DominioException;
import com.example.demo.domain.util.AccountUtil;

import javax.persistence.*;
import java.util.Objects;


public class ContaPoupanca extends Conta implements Deposita, Saque {
    @Transient
    private static final double ZERO = 0;

    public ContaPoupanca(long number, long agency, String holderCPF) {
        super(number, agency, holderCPF,  TipoConta.POUPANCA);
    }

    //igual pra todos. -> colocar em outro local evitar dup
    @Override
    public void depositar(final double valor) {
        setBalance(getBalance() + valor);
    }

    @Override
    public void sacar(double valor) {
        double newBalance = getBalance() - valor;
        if (newBalance < ZERO) {
            throw new DominioException("Saldo insuficiente");
        }
        setBalance(newBalance);
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof ContaPoupanca) {
            ContaPoupanca accountToCompare = (ContaPoupanca) o;
            ContaPoupanca thisAccount =
                    new ContaPoupanca(getNumero(), getAgencia(), getCpfTitular());

            return AccountUtil.isTheSameFields(accountToCompare, thisAccount);
        }
        return false;

    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumero(), getAgencia(), getCpfTitular());
    }

}
