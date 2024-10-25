package com.georota.georota.cidade_mapa;

public class Rua {
    Ponto destino;
    double distancia;

    // Builder
    public Rua(Ponto destino, double distancia) {
        this.destino = destino;
        this.distancia = distancia;
    }
}
