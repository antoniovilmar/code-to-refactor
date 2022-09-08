package com.example.demo.application;

import com.example.demo.api.dto.AberturaContaDto;
import com.example.demo.domain.Dependente;
import org.springframework.stereotype.Service;

@Service
public interface ContaService {
    long openAccount(final AberturaContaDto aberturaContaDto);
    void withdraw(final long accountNumber, final double value);
    void deposit(final long accountNumber, final double value);
    void includeDependent(final Dependente dependente, final long accountNumber);
}
