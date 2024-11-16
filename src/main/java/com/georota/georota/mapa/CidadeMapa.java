package com.georota.georota.mapa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import java.util.*;

public class CidadeMapa {
    public final List<Local> locais;
    private final Map<String, List<Local>> conexoes;
    // Armazenar locais temporários (Pilha) - (LIFO)
    public Stack<Local> tempLocais;
    // Fila para processar as rotas em ordem (FIFO)
    public Queue<Local> filaLocais;
    // Arvore Binária
    ArvoreBinaria arvoreBuscar = new ArvoreBinaria();

    public CidadeMapa() {
        this.locais = new ArrayList<>();
        this.conexoes = new HashMap<>();
        this.tempLocais = new Stack<>();  // Inicializando a pilha
        this.filaLocais = new LinkedList<>();  // Também inicialize a fila
    }

    public void adicionaLocal(String nomePonto, String logradouro) {
        Local local = new Local(nomePonto, logradouro);
        locais.add(local);
        conexoes.put(nomePonto, new ArrayList<>());
    }

    public void conectarPontos(String nomePontoOrigem, String nomePontoDestino) {
        Local origem = encontrarLocal(nomePontoOrigem);
        Local destino = encontrarLocal(nomePontoDestino);
        if (origem != null && destino != null) {
            origem.criarConexaoLocal(destino);
            destino.criarConexaoLocal(origem);
            conexoes.get(nomePontoOrigem).add(destino);
            conexoes.get(nomePontoDestino).add(origem);
            String distancia = GoogleMaps.getDistance(origem.getLogradouro(), destino.getLogradouro());
        }
    }

    public Local encontrarLocal(String nomePonto) {
        for (Local local : locais) {
            if (local.getNomePonto().equals(nomePonto)) {
                return local;
            }
        }
        return null;
    }

    public void printLocais() {
        for (Local local : locais) {
            System.out.println("Ponto: " + local.getNomePonto());
            System.out.println(" Conexões:");
            for (Ponto conexao : local.getConexao()) {
                String distancia = GoogleMaps.getDistance(local.getLogradouro(), conexao.getLogradouro());
                System.out.println(" - " + conexao.getNomePonto() + " Distância: " + distancia);
            }
            System.out.println();
        }
    }


    // Novos
    public void adicionarLocalTemporario(Local local) {
        tempLocais.push(local);
    }

    public Local removerLocalTemporario() {
        return tempLocais.isEmpty() ? null : tempLocais.pop();
    }

    public void adicionarLocalFila(Local local) {
        filaLocais.offer(local);
    }

    public Local removerLocalFila() {
        return filaLocais.poll();
    }

    public Local buscaLinear(String nomePonto) {
        for (Local local : locais) {
            if (local.getNomePonto().equals(nomePonto)) {
                return local;
            }
        }
        return null;
    }
}