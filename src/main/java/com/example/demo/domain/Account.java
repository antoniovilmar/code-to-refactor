package com.example.demo.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public interface Account {
    long getNumber();

    long getAgency();

    double getBalance();

    String getHolderCPF();

    void deposit(final double value);

    void withdraw(double value);

    void includeDependent(final Dependent dependent) ;
}
