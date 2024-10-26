package com.georota.georota;

import com.georota.georota.cidade_mapa.Cidade;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeorotaApplication {

    public static void main(String[] args) {
//		SpringApplication.run(GeorotaApplication.class, args);
		Cidade osasco = new Cidade();
		osasco.addPonto("City Bussocaba - Parque Municipal");
		osasco.addPonto("City Bussocaba - Escola Municipal");
		osasco.addConexao("City Bussocaba - Parque Municipal", "City Bussocaba - Escola Municipal", 2.5);
		osasco.exibirCidade();
    }

}
