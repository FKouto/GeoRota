package com.georota.georota.mapa;

import java.util.ArrayList;
import java.util.List;

public class Ponto {
    public String nome;
    List<Rua> conexoes;

    public Ponto(String nome) {
        this.nome = nome;
        this.conexoes = new ArrayList<>();
    }

    public void adicionarConexao(Ponto destino, double distancia) {
        this.conexoes.add(new Rua(destino, distancia));
    }
}
