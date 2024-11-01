package com.georota.georota;

import com.georota.georota.mapa.Cidade;
import com.georota.georota.mapa.Ponto;



import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeorotaApplication {

    public static void main(String[] args) {
//		SpringApplication.run(GeorotaApplication.class, args);
		Cidade osasco = new Cidade();
		osasco.addPonto("jandira");
		osasco.addPonto("barueri");
		osasco.addConexao("jandira", "barueri");
		osasco.exibirCidade();

		
		 System.out.println("Pontos na pilha:");
        while (!osasco.pilhaLocais.isEmpty()) {
            System.out.println(osasco.removerPontoPilha().nome);
        }

        osasco.adicionarPontoFila(osasco.buscaLinear(""));
        osasco.adicionarPontoFila(osasco.buscaLinear(""));

        System.out.println("Pontos na fila:");
        while (!osasco.filaRotas.isEmpty()) {
            System.out.println(osasco.removerPontoFila().nome);
        }
        Ponto pontoEncontradoLinear = osasco.buscaLinear("");
        System.out.println("Busca Linear: " + (pontoEncontradoLinear != null ? pontoEncontradoLinear.nome : "Não encontrado"));
    }

}
