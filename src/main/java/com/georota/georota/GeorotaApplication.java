package com.georota.georota;

import com.georota.georota.cidade_mapa.Cidade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeorotaApplication {

    public static void main(String[] args) {
//		SpringApplication.run(GeorotaApplication.class, args);
		Cidade osasco = new Cidade();
		osasco.adicionarPonto("City Bussocaba - Parque Municipal");
		osasco.adicionarPonto("City Bussocaba - Escola Municipal");
		osasco.adicionarConexao("City Bussocaba - Parque Municipal", "City Bussocaba - Escola Municipal", 2.5);
		osasco.exibirCidade();
    }

}
