package com.georota.georota.api;

// Imports Java

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Directions {
    private static final String MAPS_KEY = System.getenv("GOOGLE_MAPS_KEY");

    public static String melhorRota(String origem, String destino, String modo) {
        try {
            // Codificar os endereços de origem e destino para serem usados na URL
            String origemCodificada = URLEncoder.encode(origem, StandardCharsets.UTF_8);
            String destinoCodificado = URLEncoder.encode(destino, StandardCharsets.UTF_8);

            // Realizar a requisição HTTP para obter os dados de rota
            BufferedReader read = getBufferedReader(origemCodificada, destinoCodificado, modo);
            JsonObject json = new Gson().fromJson(read, JsonObject.class);
            read.close();

            // Verificar se a resposta da API foi bem-sucedida
            if (json != null && json.get("status").getAsString().equals("OK")) {
                // Extraímos a rota
                JsonArray routes = json.getAsJsonArray("routes");
                if (routes != null && !routes.isEmpty()) {
                    // Pegamos a primeira rota (existe possibilidade de múltiplas rotas)
                    JsonObject route = routes.get(0).getAsJsonObject();

                    // Pegamos os passos da rota (legs)
                    JsonArray legs = route.getAsJsonArray("legs");
                    if (legs != null && !legs.isEmpty()) {
                        JsonObject leg = legs.get(0).getAsJsonObject();
                        StringBuilder sb = new StringBuilder();

//                        // Adicionando informações de início e fim da rota
//                        sb.append("Início: ").append(leg.get("start_address").getAsString()).append("\n");
//                        sb.append("Fim: ").append(leg.get("end_address").getAsString()).append("\n");
//                        sb.append("Distância total: ").append(leg.getAsJsonObject("distance").get("text").getAsString()).append("\n");
                        sb.append("Duração total: ").append(leg.getAsJsonObject("duration").get("text").getAsString()).append("\n");

                        // Detalhes dos passos da rota
                        JsonArray steps = leg.getAsJsonArray("steps");
                        for (int i = 0; i < steps.size(); i++) {
                            JsonObject step = steps.get(i).getAsJsonObject();
                            sb.append("   Passo ").append(i + 1).append(": ").append(removeHtmlTags(step.get(
                                    "html_instructions").getAsString())).append("\n");
                            sb.append("    Distância: ").append(step.getAsJsonObject("distance").get("text").getAsString()).append("\n");
                            sb.append("    Duração: ").append(step.getAsJsonObject("duration").get("text").getAsString()).append("\n");
                        }

                        return sb.toString();
                    }
                }
            } else {
                return "Erro: Não foi possível encontrar uma rota.";
            }
        } catch (Exception e) {
            return "Erro ao calcular a rota: " + e.getMessage();
        }
        return "Erro desconhecido";
    }

    @NotNull
    private static BufferedReader getBufferedReader(String origem, String destino, String modo) {
        try {
            String urlFormated = String.format(
                    "https://maps.googleapis.com/maps/api/directions/json?origin=%s&destination=%s&mode=%s&language=pt-BR&key=%s",
                    origem, destino, modo, MAPS_KEY
            );
            URL url = new URI(urlFormated).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            return new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (URISyntaxException | IOException e) {
            System.out.println("Erro de sintaxe na URL: " + e.getMessage());
            return new BufferedReader(new StringReader(""));
        }
    }

    public static String removeHtmlTags(String html) {
        return html.replaceAll("<[^>]*>", "");
    }
}
