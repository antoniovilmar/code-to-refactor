package com.example.demo.infrastructure;

import com.example.demo.domain.ContaInvestimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaInvestimentoRepository extends JpaRepository<ContaInvestimento, Long> {

}
