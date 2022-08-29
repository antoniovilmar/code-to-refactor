package com.example.demo.infrastructure.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "geradorNumeroConta", url = "https://conta-numero")
public interface CustomerAccountNumberGenerator {

    @PostMapping("/criarNumero")
     long generate();
}
