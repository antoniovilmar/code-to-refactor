package com.example.demo.infrastructure;

import com.example.demo.domain.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Conta, Long> {
}
