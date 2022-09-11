package com.example.demo.application.conta.investimento;

import com.example.demo.api.dto.AberturaContaDto;
import com.example.demo.application.conta.AberturaContaService;
import com.example.demo.domain.conta.Conta;
import com.example.demo.domain.conta.ContaInvestimento;
import com.example.demo.infrastructure.ContaRepository;
import com.example.demo.infrastructure.external.GeradorNumeroContaInvestimentoFachada;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("Investimento")
public class AberturaContaInvestimentoServiceImpl implements AberturaContaService {

    private final GeradorNumeroContaInvestimentoFachada geradorNumeroContaInvestimentoFachada;
    private final ContaRepository contaRepository;

    public AberturaContaInvestimentoServiceImpl(GeradorNumeroContaInvestimentoFachada geradorNumeroContaInvestimentoFachada, ContaRepository contaRepository) {
        this.geradorNumeroContaInvestimentoFachada = geradorNumeroContaInvestimentoFachada;
        this.contaRepository = contaRepository;
    }

    @Override
    public long abrir(AberturaContaDto aberturaContaDto) {
        final long numeroConta = geradorNumeroContaInvestimentoFachada.gerar(aberturaContaDto.getCpfTitular());
        final Conta contaParaCriar = new ContaInvestimento(numeroConta, aberturaContaDto.getAgencia(), aberturaContaDto.getCpfTitular());
        final Conta contaCriada = contaRepository.save(contaParaCriar);

        return contaCriada.getNumero();
    }

}
