package com.example.demo.domain.conta;

import com.example.demo.domain.Dependente;
import com.example.demo.domain.TipoConta;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ContaCorrente extends Conta {

    private ContaCorrente() {
        super();
    }
    @OneToMany
    private Set<Dependente> dependentes;

    public ContaCorrente(long number, long agency, String holderCPF) {
        super(number, agency, holderCPF, TipoConta.CORRENTE);
        dependentes = new HashSet<>();
    }

    @Override
    protected double getSaldoMinimoPermitido() {
        return -1000;
    }

    public void incluirDependente(final Dependente dependente) {
        this.dependentes.add(dependente);
    }

}
