package com.georota.georota;

import com.georota.georota.mapa.Cidade;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeorotaApplication {

    public static void main(String[] args) {
//		SpringApplication.run(GeorotaApplication.class, args);
		Cidade osasco = new Cidade();
		osasco.addPonto("");
		osasco.addPonto("");
		osasco.addConexao("", "");
		osasco.exibirCidade();
    }

}
