package com.georota.georota.maps.services;

import com.georota.georota.arvoreBinaria.ArvoreBinaria;
import com.georota.georota.google.Directions;
import com.georota.georota.google.DistanceMatrix;
import com.georota.georota.maps.entities.Local;
import com.georota.georota.maps.entities.Ponto;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Cidade {
    private final List<Local> locais = new ArrayList<>();
    private final Map<String, List<Local>> elo = new HashMap<>();

    public void adicionarLocal(String nomePonto, String logradouro) {
        Local local = new Local(nomePonto, logradouro);
        locais.add(local);
        elo.put(nomePonto, new ArrayList<>());
    }

    // Arvore Binária
    ArvoreBinaria arvoreBuscar = new ArvoreBinaria();

    public void conectarElos(String nomeOrigem, String nomeDestino) {
        Local origem = encontrarPontoPorNome(nomeOrigem);
        Local destino = encontrarPontoPorNome(nomeDestino);
        if (origem != null && destino != null) {
            origem.criarEloLocal(destino);
            destino.criarEloLocal(origem);
            elo.get(nomeOrigem).add(destino);
            elo.get(nomeDestino).add(origem);
        }
    }

    public Local encontrarPontoPorNome(String nomePonto) {
        for (Local local : locais) {
            if (local.getNomePonto().equals(nomePonto)) {
                return local;
            }
        }
        return null;
    }

    public boolean existePonto(String nomePonto) {
        return encontrarPontoPorNome(nomePonto) != null;
    }

    public String connections() {
        List<Map<String, Object>> connectionsList = new ArrayList<>();
        for (Local local : locais) {
            Map<String, Object> connectionMap = new HashMap<>();
            connectionMap.put("Ponto", local.getNomePonto());
            List<Map<String, String>> elosList = new ArrayList<>();
            for (Ponto elo : local.getElo()) {
                Map<String, String> eloMap = new HashMap<>();
                try {
                    String distancia = DistanceMatrix.obterDistancia(local.getLogradouro(), elo.getLogradouro());
                    eloMap.put("Nome", elo.getNomePonto());
                    eloMap.put("Distância", distancia);
                } catch (Exception e) {
                    eloMap.put("Nome", elo.getNomePonto());
                    eloMap.put("Distância", "Erro ao obter a distância: " + e.getMessage());
                }
                elosList.add(eloMap);
            }
            connectionMap.put("Conexões", elosList);
            connectionsList.add(connectionMap);
        }
        return new Gson().toJson(connectionsList);
    }

    public String obterMelhorRotaEntrePontos(String nomeOrigem, String nomeDestino, String modo) {
        Local origem = encontrarPontoPorNome(nomeOrigem);
        Local destino = encontrarPontoPorNome(nomeDestino);
        if (origem != null && destino != null) {
            return Directions.melhorRota(origem.getLogradouro(), destino.getLogradouro(), modo);
        }
        return "Erro: Um ou ambos os pontos não existem.";
    }
     // Arvore Binária
     public void adicionarLocalArvore(Local local) {
        System.out.println("Adicionando local à árvore: " + local.getNomePonto());
        arvoreBuscar.adicionar(local);
        arvoreBuscar.listarLocaisArvore(); // verifica a arvore
    }

    public Local buscarLocalArvore(String nomePonto) {
        nomePonto = nomePonto.trim().toLowerCase();
        for (Local local : locais) {
            System.out.println("Verificando local na árvore: " + local.getNomePonto());
            if (local.getNomePonto().trim().toLowerCase().equals(nomePonto)) {
                return local;
            }
        }
        return null;
    }
    
    
}