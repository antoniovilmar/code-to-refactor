package com.example.demo.application;

import com.example.demo.api.dto.AberturaContaDto;
import com.example.demo.api.dto.TransacaoContaDto;
import com.example.demo.domain.Dependente;
import org.springframework.stereotype.Service;

@Service
public interface ContaService {
    long openAccount(final AberturaContaDto aberturaContaDto);
    void sacar(TransacaoContaDto transacaoContaDto);
    void depositar(final long accountNumber, final double value);
    void incluirDependentes(final Dependente dependente, final long accountNumber);
}
