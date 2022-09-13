package com.example.demo.application.conta.poupanca;

import com.example.demo.api.dto.AberturaContaDto;
import com.example.demo.application.conta.AberturaContaService;
import com.example.demo.domain.TipoConta;
import com.example.demo.domain.conta.Conta;
import com.example.demo.domain.conta.ContaPoupanca;
import com.example.demo.domain.conta.ContaRepository;
import com.example.demo.infrastructure.external.GeradorNumeroConta;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("Poupanca")
public class AberturaContaPoupancaServiceImpl implements AberturaContaService {

    private final GeradorNumeroConta geradorNumeroConta;
    private final ContaRepository contaRepository;

    public AberturaContaPoupancaServiceImpl(GeradorNumeroConta geradorNumeroConta, ContaRepository contaRepository) {
        this.geradorNumeroConta = geradorNumeroConta;
        this.contaRepository = contaRepository;
    }

    @Override
    public long abrir(AberturaContaDto aberturaContaDto) {
        final long numeroConta = geradorNumeroConta.gerar(TipoConta.POUPANCA);
        final Conta contaParaCriar = new ContaPoupanca(numeroConta, aberturaContaDto.getAgencia(), aberturaContaDto.getCpfTitular());
        final Conta contaCriada = contaRepository.salvar(contaParaCriar);

        return contaCriada.getNumero();
    }

}
