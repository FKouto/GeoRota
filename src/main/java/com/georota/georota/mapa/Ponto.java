package com.georota.georota.mapa;

/*
    Representa um ponto específico dentro da Rua, Ponto de Referência ou Local da Cidade.
*/

// Importação das anotações do Lombok para geração automática de getters e setters

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Ponto extends Rua {
    private String nomePonto;

    /**
     * @param nomePonto representa o nome do ponto de Referência da Rua informada.
     */
    public Ponto(String logradouro, String nomePonto) {
        super(logradouro);
        this.nomePonto = nomePonto;
    }
}
