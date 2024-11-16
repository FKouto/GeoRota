package com.georota.georota.algoritmo;

import com.georota.georota.maps.entities.Local;

public class ArvoreBinaria {
    private Node raiz;

    public void adicionar(Local local) {
        raiz = adicionarRecursivo(raiz, local);
    }

    private Node adicionarRecursivo(Node raiz, Local local) {
        if (raiz == null) {
            raiz = new Node(local);
            return raiz;
        }
        if (local.getNomePonto().compareTo(raiz.local.getNomePonto()) < 0) {
            raiz.esquerda = adicionarRecursivo(raiz.esquerda, local);
        }
        if (local.getNomePonto().compareTo(raiz.local.getNomePonto()) > 0) {
            raiz.direita = adicionarRecursivo(raiz.direita, local);
        }
        return raiz;
    }

    public Local buscar(String nomePonto) {
        return buscarRecursivo(raiz, nomePonto);
    }

    private Local buscarRecursivo(Node raiz, String nomePonto) {
        if (raiz == null || raiz.local.getNomePonto().equals(nomePonto)) {
            return raiz != null ? raiz.local : null;
        }
        if (nomePonto.compareTo(raiz.local.getNomePonto()) < 0) {
            return buscarRecursivo(raiz.esquerda, nomePonto);
        }
        return buscarRecursivo(raiz.direita, nomePonto);
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
