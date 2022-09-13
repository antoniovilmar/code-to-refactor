package com.example.demo.application.conta.investimento;

import com.example.demo.api.dto.AberturaContaDto;
import com.example.demo.application.conta.AberturaContaService;
import com.example.demo.domain.conta.Conta;
import com.example.demo.domain.conta.ContaInvestimento;
import com.example.demo.domain.conta.ContaRepository;
import com.example.demo.infrastructure.external.GeradorNumeroContaInvestimento;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("Investimento")
public class AberturaContaInvestimentoServiceImpl implements AberturaContaService {

    private final GeradorNumeroContaInvestimento geradorNumeroContaInvestimento;
    private final ContaRepository contaRepository;

    public AberturaContaInvestimentoServiceImpl(GeradorNumeroContaInvestimento geradorNumeroContaInvestimento, ContaRepository contaRepository) {
        this.geradorNumeroContaInvestimento = geradorNumeroContaInvestimento;
        this.contaRepository = contaRepository;
    }

    @Override
    public long abrir(AberturaContaDto aberturaContaDto) {
        final long numeroConta = geradorNumeroContaInvestimento.gerar(aberturaContaDto.getCpfTitular());
        final Conta contaParaCriar = new ContaInvestimento(numeroConta, aberturaContaDto.getAgencia(), aberturaContaDto.getCpfTitular());
        final Conta contaCriada = contaRepository.salvar(contaParaCriar);

        return contaCriada.getNumero();
    }

}
