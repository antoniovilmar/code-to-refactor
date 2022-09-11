package com.example.demo.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Conta {
    @Id
    private long numero;
    private long agencia;
    private String cpfTitular;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Dependente> dependentes;
    private double saldo;

    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;

    @Transient
    private double LIMITE_CHEQUE_ESPECIAL = -1000;

    protected Conta() {
        this.dependentes = new HashSet();
    }

    public Conta(final String cpfTitular, final TipoConta tipoConta, final long numeroConta, final long agencia) {
        this();
        this.cpfTitular = cpfTitular;
        this.tipoConta = tipoConta;
        this.numero = numeroConta;
        this.agencia = agencia;
    }

    public void depositar(final double valor) {
        this.saldo += valor;
    }

    public void sacar(double valor) {
        if (TipoConta.INVESTIMENTO.equals(this.tipoConta)) {
            throw new DomainBusinessException("Conta de Investimento não pode sacar, somente transferir");
        }

        double novoSaldo = saldo - valor;

        if (TipoConta.POUPANCA.equals(this.tipoConta)) {
            if (novoSaldo < 0) {
                throw new DomainBusinessException("Saldo insuficiente");
            }
        } else {
            if (novoSaldo < LIMITE_CHEQUE_ESPECIAL) {
                throw new DomainBusinessException("Saldo insuficiente");
            }
        }

        this.saldo = novoSaldo;
    }

    public void incluirDependente(String cpf, String telefone) {
        if (TipoConta.INVESTIMENTO.equals(this.tipoConta) || TipoConta.POUPANCA.equals(this.tipoConta)) {
            throw new DomainBusinessException("Conta de Investimento/Poupança não pode ter dependentes");
        }
        Dependente dependente = new Dependente(cpf, telefone);
        this.dependentes.add(dependente);

    }

    public void removerDependente(String cpf) {
        this.dependentes.removeIf(dependente -> dependente.getCpf().equals(cpf));
    }

    public double getSaldo() {
        return saldo;
    }

    public long getNumero() {
        return numero;
    }

    public long getAgencia() {
        return agencia;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Conta)) return false;
        Conta conta = (Conta) o;
        return getNumero() == conta.getNumero();
    }

    public Set<Dependente> getDependentes() {
        return dependentes;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getNumero());
    }

}
