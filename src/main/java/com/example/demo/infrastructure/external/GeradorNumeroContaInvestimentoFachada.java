package com.example.demo.infrastructure.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

public interface GeradorNumeroContaInvestimentoFachada {

    long gerar(String cpf);
}
