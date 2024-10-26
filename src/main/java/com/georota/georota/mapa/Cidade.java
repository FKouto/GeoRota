package com.georota.georota.mapa;

import java.util.ArrayList;
import java.util.List;

public class Cidade {
    List<Ponto> pontos;
    private String distanciaPonto;

    public Cidade() {
        this.pontos = new ArrayList<>();
    }

    public void addPonto(String nome) {
        Ponto novoPonto = new Ponto(nome);
        pontos.add(novoPonto);
    }

    public void addConexao(String nomeOrigem, String nomeDestino) {
        Ponto origem = encontrarPonto(nomeOrigem);
        Ponto destino = encontrarPonto(nomeDestino);

        if (origem != null && destino != null) {
            try {
                distanciaPonto = GoogleMaps.getDistance(nomeOrigem.replace(" ", "+"),nomeDestino.replace(" ",
                        "+"));
                origem.adicionarConexao(destino, 0.0);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
                    System.out.println("  Conectado a: " + rua.destino.nome + " (Distância: " + distanciaPonto + ")");
                }
            }
        }
    }
}
