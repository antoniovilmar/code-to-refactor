package com.example.demo.infrastructure.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "geradorNumeroConta", url = "https://conta-numero")
public interface GeradorNumeroContaClient {

    @PostMapping("/criarNumero")
     long gerar();
}
