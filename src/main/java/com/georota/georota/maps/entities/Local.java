package com.georota.georota.maps.entities;
// Importação das anotações do Lombok para geração automática de getters e setters
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Local extends Ponto {
    private double distancia;
    private List<Ponto> conexao;

    // Construtor que inicializa o campo "nomePonto" e "logradouro" através do construtor da classe pai
    public Local(String nomePonto, String logradouro) {
        super(logradouro, nomePonto);
        this.conexao = new ArrayList<>();
    }

    public Local(String nomePonto, String logradouro, double distancia) {
        super(logradouro, nomePonto);
        this.distancia = distancia;
    }

    // Método que adiciona um ponto à lista de conexões ("conexao")
    public void criarConexaoLocal(Ponto ponto) {
        this.conexao.add(ponto);
    }
}
