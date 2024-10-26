package com.georota.georota.cidade_mapa;

import java.util.ArrayList;
import java.util.List;

public class Cidade {
    List<Ponto> pontos;

    public Cidade() {
        this.pontos = new ArrayList<>();
    }
    public void adicionarPonto(String nome) {
        Ponto novoPonto = new Ponto(nome);
        pontos.add(novoPonto);
    }
    public void adicionarConexao(String nomeOrigem, String nomeDestino, double distancia) {
        Ponto origem = encontrarPonto(nomeOrigem);
        Ponto destino = encontrarPonto(nomeDestino);

        if (origem != null && destino != null) {
            origem.adicionarConexao(destino, distancia);
        }
    }
    private Ponto encontrarPonto(String nome) {
        for (Ponto ponto : pontos) {
            if (ponto.nome.equals(nome)) {
                return ponto;
            }
        }
        return null;
    }
    public void exibirCidade() {
        for (Ponto ponto : pontos) {
            System.out.println("Ponto: " + ponto.nome);
            if (ponto.conexoes.isEmpty()) {
                System.out.println(" Sem pontos de conexão");
            }
            if (ponto.conexoes != null) {
                for (Rua rua : ponto.conexoes) {
                    System.out.println("  Conectado a: " + rua.destino.nome + " (Distância: " + rua.distancia + ")");
                }
            }
        }
    }
}
