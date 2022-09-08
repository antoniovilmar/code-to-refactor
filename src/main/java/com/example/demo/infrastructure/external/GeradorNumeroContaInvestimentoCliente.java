package com.example.demo.infrastructure.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "geradorNumeroInvestimento", url = "https://conta-investimento-numero")
public interface GeradorNumeroContaInvestimentoCliente {

    @PostMapping("/geracao")
    long generate(String cpf);
}
