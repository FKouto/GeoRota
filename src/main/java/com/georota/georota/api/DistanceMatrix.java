package com.georota.georota.api;

// Imports Java
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.*;

public class DistanceMatrix {
    private static final String MAPS_KEY = System.getenv("GOOGLE_MAPS_KEY");

    /**
     * Obtém a distância entre dois locais.
     *
     * @param origem Local de origem para calcular a distância
     * @param destino Local de destino para retornar distância
     * @return distância entre o local, origem e destino
     */
    public static String obterDistancia(String origem, String destino) {
        try {
            // Codificando os locais para a URL
            String origemCodificada = URLEncoder.encode(origem, "UTF-8");
            String destinoCodificado = URLEncoder.encode(destino, "UTF-8");

            BufferedReader read = getBufferedReader(origemCodificada, destinoCodificado);
            JsonObject json = new Gson().fromJson(read, JsonObject.class);
            read.close();

            if (json != null && json.get("status").getAsString().equals("OK")) {
                JsonObject elements = json.getAsJsonArray("rows")
                        .get(0)
                        .getAsJsonObject()
                        .getAsJsonArray("elements")
                        .get(0)
                        .getAsJsonObject();
                // Obtém a distância entre os pontos em Metros
                int distanciaMetros = elements.getAsJsonObject("distance").get("value").getAsInt();
                // Formata o retorno com base no valor recebido
                return (distanciaMetros < 1000)
                        ? distanciaMetros + " metros"
                        : String.format("%.2f km", distanciaMetros / 1000.0);
            }
            return "Distância não encontrada ou erro na requisição.";
        } catch (Exception e) {
            e.printStackTrace(); // Imprime o stack trace para ajudar na depuração
            return "Erro ao obter a distância: " + e.getMessage();
        }
    }

    @NotNull
    private static BufferedReader getBufferedReader(String origem, String destino) {
        try {
            // Formata a URL com as origens e destinos codificados
            String urlFormated = String.format(
                    "https://maps.googleapis.com/maps/api/distancematrix/json?origins=%s&destinations=%s&key=%s",
                    origem, destino, MAPS_KEY
            );
            URL url = new URI(urlFormated).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // Timeout para requisições
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            // Ler a resposta
            return new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (URISyntaxException | IOException e) {
            System.out.println("Erro de sintaxe na URL: " + e.getMessage());
            return new BufferedReader(new StringReader("")); // Retorna uma string vazia em caso de erro
        }
    }
}