package com.example.demo.infrastructure.external;

import com.example.demo.domain.TipoConta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
public interface GeradorNumeroContaFachada {

    long gerar(TipoConta tipoConta);
}
