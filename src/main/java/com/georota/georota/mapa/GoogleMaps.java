package com.georota.georota.mapa;

// Imports Java
import java.io.BufferedReader; // Lê texto de um fluxo de entrada de forma eficiente, utilizando um buffer para melhorar o desempenho.
import java.io.IOException; // Exceção verificada lançada para sinalizar falhas de entrada/saída (I/O), como erros de leitura ou escrita.
import java.io.InputStreamReader; // Converte fluxos de bytes em caracteres, geralmente usado para ler dados de conexões de rede ou arquivos.
import java.io.StringReader; // Lê dados de uma string como se fosse um fluxo de entrada (útil para simulações ou testes).
import java.net.HttpURLConnection; // Classe para enviar solicitações HTTP e receber respostas de servidores.
import java.net.URI; // Representa um URI (Identificador Uniforme de Recursos), como uma URL, mas com mais flexibilidade.
import java.net.URISyntaxException; // Exceção lançada quando há um problema ao criar ou interpretar uma URI inválida.
import java.net.URL; // Representa uma URL (Localizador Uniforme de Recursos), usada para acessar recursos na web.

// Imports Google
import com.google.gson.JsonObject; // Classe para representar um objeto JSON (estrutura chave-valor), utilizada na manipulação de dados JSON.
import com.google.gson.Gson; // Classe principal da biblioteca Gson, utilizada para converter objetos Java em JSON e vice-versa.

// Imports IDE (JetBrains/IntelliJ)
import org.jetbrains.annotations.NotNull; // Anotação que indica que um parâmetro ou retorno de método não pode ser nulo.

public class GoogleMaps {
    private static final String MAPS_KEY = "chave apis";

    /**
     * Obter a distância entre dois pontos.
     *
     * @param origem  O ponto inicial.
     * @param destino O ponto final.
     * @return A distância entre o ponto inicial e final.
     */
    public static String getDistance(String origem, String destino) {
        try {
            BufferedReader read = getBufferedReader(origem, destino);
            JsonObject json = new Gson().fromJson(read, JsonObject.class);
            read.close();
            if (json.get("status").getAsString().equals("OK")) {
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
            throw new RuntimeException(e);
        }
    }

    @NotNull
    private static BufferedReader getBufferedReader(String origem, String destino) throws URISyntaxException, IOException {
        try {
            // Formata a URL
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
            return new BufferedReader(new StringReader(""));
        }
    }
}
