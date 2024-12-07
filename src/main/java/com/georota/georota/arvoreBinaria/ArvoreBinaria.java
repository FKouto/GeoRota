package com.georota.georota.arvoreBinaria;

import com.georota.georota.maps.entities.Local;

public class ArvoreBinaria {
    private Node raiz;

    public void adicionar(Local local) {
        System.out.println("Adicionando local: " + local.getNomePonto());
        raiz = adicionarRecursivo(raiz, local);
    }

    private Node adicionarRecursivo(Node raiz, Local local) {
        String nomePonto = local.getNomePonto().trim().toLowerCase();
        if (raiz == null) {
            raiz = new Node(local);
            return raiz;
        }
        String nomeRaiz = raiz.local.getNomePonto().trim().toLowerCase();
        if (nomePonto.compareTo(nomeRaiz) < 0) {
            raiz.esquerda = adicionarRecursivo(raiz.esquerda, local);
        } else if (nomePonto.compareTo(nomeRaiz) > 0) {
            raiz.direita = adicionarRecursivo(raiz.direita, local);
        }
        return raiz;
    }

    private Local buscarRecursivo(Node raiz, String nomePonto) {
        nomePonto = nomePonto.trim().toLowerCase(); // Normaliza o nome para comparação
        if (raiz == null || raiz.local.getNomePonto().trim().toLowerCase().equals(nomePonto)) {
            return raiz != null ? raiz.local : null;
        }
        if (nomePonto.compareTo(raiz.local.getNomePonto().trim().toLowerCase()) < 0) {
            return buscarRecursivo(raiz.esquerda, nomePonto);
        }
        return buscarRecursivo(raiz.direita, nomePonto);
    }
    

    public Local buscar(String nomePonto) {
        System.out.println("Buscando local: " + nomePonto);
        return buscarRecursivo(raiz, nomePonto);
    }

    public void listarLocaisArvore() {
        System.out.println("Locais na árvore:");
        listarRecursivo(raiz);
    }

    private void listarRecursivo(Node raiz) {
        if (raiz != null) {
            listarRecursivo(raiz.esquerda);
            System.out.println("Local: " + raiz.local.getNomePonto());
            listarRecursivo(raiz.direita);
        }
    }

    private class Node {
        Local local;
        Node esquerda, direita;

        public Node(Local local) {
            this.local = local;
            esquerda = direita = null;
        }
    }
}
