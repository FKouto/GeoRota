package com.georota.georota.mapa;

public class ArvoreBinaria {
    private No raiz;
    
    private class No {
        Ponto ponto;
        No esq, dir;

        public No(Ponto ponto){
            this.ponto = ponto;
            esq = dir = null;
        }
    };

    public void adicionar(Ponto ponto) {
        raiz = adicionarRecursivo(raiz, ponto);
    }

    private No adicionarRecursivo(No raiz, Ponto ponto){
        if(raiz == null){
            raiz = new No(ponto);
            return raiz;
        }

        if(ponto.nome.compareTo(raiz.ponto.nome) < 0){
            raiz.esq = adicionarRecursivo(raiz.esq, ponto);
        } else if (ponto.nome.compareTo(raiz.ponto.nome) > 0){
            raiz.dir = adicionarRecursivo(raiz.dir, ponto);
        }
        return raiz;
    }  

        public Ponto buscar(String nome){
            return buscarRecursivo(raiz, nome);
        }

    //Método recursivo para buscar um ponto na árvore
    private Ponto buscarRecursivo(No raiz, String nome) {
        if(raiz == null || raiz.ponto.nome.equals(nome)){
            return raiz != null ? raiz.ponto : null;
        }

        if(nome.compareTo(raiz.ponto.nome) < 0){
            return buscarRecursivo(raiz.esq, nome);
        }

        return buscarRecursivo(raiz.dir, nome);
    }
}