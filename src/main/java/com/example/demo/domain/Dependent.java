package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Dependent {
    @Id
    private String CPF;
    private String phone;

    public Dependent() {
    }

    public Dependent(String CPF, String phone) {
        this.CPF = CPF;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Dependent) {
            Dependent that = (Dependent) o;
            return Objects.equals(CPF, that.CPF) && Objects.equals(phone, that.phone);
        }else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(CPF, phone);
    }
}
