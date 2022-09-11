package com.example.demo.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

class MovimentacaoTest {

    @Test
    void deveTestarEqualsAndHashCode() {
        EqualsVerifier.forClass(Movimentacao.class)
                .withNonnullFields("id")
                .suppress(Warning.SURROGATE_KEY)
                .verify();
    }

}