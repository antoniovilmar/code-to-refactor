package com.example.demo.application.conta.poupanca;

import com.example.demo.api.dto.AberturaContaDto;
import com.example.demo.application.conta.AberturaContaService;
import com.example.demo.domain.TipoConta;
import com.example.demo.domain.conta.Conta;
import com.example.demo.domain.conta.ContaPoupanca;
import com.example.demo.infrastructure.ContaRepository;
import com.example.demo.infrastructure.external.GeradorNumeroContaFachada;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("Poupanca")
public class AberturaContaPoupancaServiceImpl implements AberturaContaService {

    private final GeradorNumeroContaFachada geradorNumeroContaFachada;
    private final ContaRepository contaRepository;

    public AberturaContaPoupancaServiceImpl(GeradorNumeroContaFachada geradorNumeroContaFachada, ContaRepository contaRepository) {
        this.geradorNumeroContaFachada = geradorNumeroContaFachada;
        this.contaRepository = contaRepository;
    }

    @Override
    public long abrir(AberturaContaDto aberturaContaDto) {
        final long numeroConta = geradorNumeroContaFachada.gerar(TipoConta.POUPANCA);
        final Conta contaParaCriar = new ContaPoupanca(numeroConta, aberturaContaDto.getAgencia(), aberturaContaDto.getCpfTitular());
        final Conta contaCriada = contaRepository.save(contaParaCriar);

        return contaCriada.getNumero();
    }

}
