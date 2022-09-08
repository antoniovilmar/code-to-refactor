package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Dependente {
    @Id
    private String cpf;
    private String phone;

    public Dependente() {
    }

    public Dependente(String cpf, String phone) {
        this.cpf = cpf;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Dependente) {
            Dependente that = (Dependente) o;
            return Objects.equals(cpf, that.cpf) && Objects.equals(phone, that.phone);
        }else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf, phone);
    }
}
