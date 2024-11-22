package com.georota.georota.app;

import com.georota.georota.maps.services.CidadeMapa;
import com.georota.georota.maps.entities.Local;

import javax.swing.JOptionPane;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeorotaApplication {

    public static void main(String[] args) {
        CidadeMapa cidade = new CidadeMapa();

        String novoLocal = JOptionPane.showInputDialog("Digite um novo local de interesse");
        String novoLogradouro = JOptionPane.showInputDialog("Digite o novo logradouro");

        Local l =  new Local(novoLocal, novoLogradouro);

        // Adiciona alguns locais
        cidade.adicionaLocal(novoLocal, novoLogradouro);  //"Praça da Sé", "Se, São Paulo - SP, 01001-000"

        JOptionPane.showMessageDialog(null, l.getNomePonto() + " , " + l.getLogradouro());

        cidade.adicionaLocal("MorumBIS", "Praça Roberto Gomes Pedrosa, 1 - Morumbi, São Paulo - SP, 05653-070");
        cidade.adicionaLocal("Parque Ibirapuera", "Av. Pedro Álvares Cabral - Vila Mariana, São Paulo - SP, 04094-050");

        // Conecta pontos de interesse (Rua)
        cidade.conectarPontos("Praça da Sé", "MorumBIS");
        cidade.conectarPontos("Praça da Sé", "Parque Ibirapuera");

        // Imprime os locais e suas conexões
        cidade.printLocais();

        cidade.adicionarLocalTemporario(cidade.buscaLinear("Praça da Sé"));
        cidade.adicionarLocalTemporario(cidade.buscaLinear("Parque Ibirapuera"));

        System.out.println("Pontos na Pilha:");
        while (!cidade.tempLocais.isEmpty()) {
            System.out.println(cidade.removerLocalTemporario().getNomePonto());
        }

        cidade.adicionarLocalFila(cidade.buscaLinear("MorumBIS"));

        System.out.println("\nPontos na Fila:");
        while (!cidade.filaLocais.isEmpty()) {
            System.out.println(cidade.removerLocalFila().getNomePonto());
        }

        Local buscar = cidade.buscaLinear("Parque Ibirapuera");
        System.out.println("\nBusca Linear: " + (buscar != null ? buscar.getNomePonto() : "Não encontrado"));

        System.out.println("\nArvore Binária");
        for (Local local : cidade.locais) {
            cidade.adicionarLocalArvore(local);
        }

        Local arvoreBusca = cidade.buscarLocalArvore("Parque Ibirapuera");
        System.out.println("Ponto encontrado na árvore: " + (arvoreBusca != null ? arvoreBusca.getNomePonto() : "Não encontrado"));

    }
}
