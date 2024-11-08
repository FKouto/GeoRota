package com.georota.georota.mapa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;



public class Cidade {
    ArvoreBinaria arvoreBusca = new ArvoreBinaria();

    //Método para adicionar um ponto à árvore binária
    public void adicionarPontoArvore(Ponto ponto){
        arvoreBusca.adicionar(ponto);
    }

    //Método para buscar um ponto na árvore binária
    public Ponto buscarPontoArvore(String nome){
        return arvoreBusca.buscar(nome);
    }
    
    public List<Ponto> pontos;
    private String distanciaPonto;
     // Pilha para armazenar locais temporários
     public Stack<Ponto> pilhaLocais;

    // Fila para processar rotas em ordem
    public Queue<Ponto> filaRotas;
    

    public Cidade() {
        this.pontos = new ArrayList<>();
        this.pilhaLocais = new Stack<>();
        this.filaRotas =   new LinkedList<>();
        
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
    /**
     * Adiciona um ponto à pilha de locais temporários.
     * 
     * @param ponto O ponto a ser adicionado à pilha.
     */
    public void adicionarPontoPilha(Ponto ponto){
        pilhaLocais.push(ponto);
    }

     /**
     * Remove e retorna o último ponto adicionado à pilha de locais temporários.
     * 
     * @return O ponto removido da pilha, ou null se a pilha estiver vazia.
     */
public Ponto removerPontoPilha(){
    return pilhaLocais.isEmpty() ? null : pilhaLocais.pop();

}
 /**
     * Adiciona um ponto à fila de rotas.
     * 
     * @param ponto O ponto a ser adicionado à fila.
     */
    public void adicionarPontoFila(Ponto ponto) {
        filaRotas.offer(ponto);
    }
      /**
     * Remove e retorna o primeiro ponto da fila de rotas.
     * 
     * @return O ponto removido da fila, ou null se a fila estiver vazia.
     */
    public Ponto removerPontoFila() {
        return filaRotas.poll();
    }

    public Ponto buscaLinear(String nome) {
        for (Ponto ponto : pontos) {
            if (ponto.nome.equals(nome)) {
                return ponto;
            }
        }
        return null;
    }
}
