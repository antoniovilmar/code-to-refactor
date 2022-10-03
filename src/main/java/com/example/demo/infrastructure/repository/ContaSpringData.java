package com.example.demo.infrastructure.repository;

import com.example.demo.domain.ContaCorrentePoupanca;
import org.springframework.data.jpa.repository.JpaRepository;

interface ContaSpringData extends JpaRepository<ContaCorrentePoupanca, Long> {

}