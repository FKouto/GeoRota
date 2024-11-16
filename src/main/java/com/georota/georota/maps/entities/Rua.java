package com.georota.georota.maps.entities;

/*
  Representa um logradouro da Cidade
  Serve como classe base para outras classes que têm nome de uma Rua como pontos de interesse na cidade.
*/

// Importação das anotações do Lombok para geração automática de getters e setters

import lombok.Getter;
import lombok.Setter;

// Declaração de uma classe abstrata chamada "Rua"
@Getter
@Setter
public abstract class Rua {
    private String logradouro;

    /**
     * @param logradouro Nome da Rua.
     */
    public Rua(String logradouro) {
        this.logradouro = logradouro;
    }
}
