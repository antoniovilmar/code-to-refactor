package com.example.demo.infrastructure.conta;

import com.example.demo.domain.conta.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

interface ContaSpringData extends JpaRepository<Conta, Long> {
}