package com.example.demo.application.conta.corrente;

import com.example.demo.api.dto.AberturaContaDto;
import com.example.demo.application.conta.AberturaContaService;
import com.example.demo.domain.TipoConta;
import com.example.demo.domain.conta.Conta;
import com.example.demo.domain.conta.ContaCorrente;
import com.example.demo.domain.conta.ContaInvestimento;
import com.example.demo.infrastructure.ContaRepository;
import com.example.demo.infrastructure.external.GeradorNumeroContaFachada;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("Corrente")
public class AberturaContaCorrenteServiceImpl implements AberturaContaService {

    private final GeradorNumeroContaFachada geradorNumeroContaFachada;
    private final ContaRepository contaRepository;

    public AberturaContaCorrenteServiceImpl(GeradorNumeroContaFachada geradorNumeroContaFachada, ContaRepository contaRepository) {
        this.geradorNumeroContaFachada = geradorNumeroContaFachada;
        this.contaRepository = contaRepository;
    }

    @Override
    public long abrir(AberturaContaDto aberturaContaDto) {
        final long numeroConta = geradorNumeroContaFachada.gerar(TipoConta.CORRENTE);
        final Conta contaParaCriar = new ContaCorrente(numeroConta, aberturaContaDto.getAgencia(), aberturaContaDto.getCpfTitular());
        final Conta contaCriada = contaRepository.save(contaParaCriar);

        return contaCriada.getNumero();
    }

}
