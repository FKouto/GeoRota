package com.georota.georota.controller;

import com.georota.georota.maps.entities.Local;
import com.georota.georota.maps.services.Cidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mapa")
@CrossOrigin
public class MapaController {

    private final Cidade cidade;

    @Autowired
    public MapaController(Cidade cidade) {
        this.cidade = cidade;
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> adicionarLocal(@RequestParam String nomePonto,
            @RequestParam String logradouro) {
        cidade.adicionarLocal(nomePonto, logradouro);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Local adicionado com sucesso.");
        response.put("Local", nomePonto);
        response.put("Rua", logradouro);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/connect")
    public ResponseEntity<Map<String, String>> conectarElos(@RequestParam String nomeOrigem,
            @RequestParam String nomeDestino) {
        Map<String, String> response = new HashMap<>();
        if (!cidade.existePonto(nomeOrigem) && !cidade.existePonto(nomeDestino)) {
            response.put("error", "Erro: Os pontos de origem e destino não existem.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        if (!cidade.existePonto(nomeOrigem)) {
            response.put("error", "Erro: O ponto de origem " + nomeOrigem + " não existe.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        if (!cidade.existePonto(nomeDestino)) {
            response.put("error", "Erro: O ponto de destino " + nomeDestino + " não existe.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        cidade.conectarElos(nomeOrigem, nomeDestino);
        response.put("message", "Pontos conectados com sucesso entre " + nomeOrigem + " e " + nomeDestino);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/connections")
    public ResponseEntity<String> getConnections() {
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(cidade.connections());
    }

    @GetMapping("/melhorota")
    public ResponseEntity<String> obterMelhorRota(@RequestParam String nomeOrigem, @RequestParam String nomeDestino,
            @RequestParam String modo) {
        String rota = cidade.obterMelhorRotaEntrePontos(nomeOrigem, nomeDestino, modo);
        if (rota.startsWith("Erro")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("Content-Type", "application/json")
                    .body(rota);
        }
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(rota);
    }

    @GetMapping("/arvore/buscar")
    public ResponseEntity<Map<String, String>> buscarConexaoNaArvore(@RequestParam String nomeOrigem, @RequestParam String nomeDestino) {
    Local origem = cidade.buscarLocalArvore(nomeOrigem);
    Local destino = cidade.buscarLocalArvore(nomeDestino);
    Map<String, String> response = new HashMap<>();

    if (origem == null) {
        response.put("error", "Ponto de origem não encontrado na árvore: " + nomeOrigem);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    if (destino == null) {
        response.put("error", "Ponto de destino não encontrado na árvore: " + nomeDestino);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    response.put("message", "Ambos os pontos foram encontrados na árvore.");
    response.put("nomeOrigem", origem.getNomePonto());
    response.put("logradouroOrigem", origem.getLogradouro());
    response.put("nomeDestino", destino.getNomePonto());
    response.put("logradouroDestino", destino.getLogradouro());

    return ResponseEntity.ok(response);
}

}