package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import model.DrugDataLoader;
import model.DrugRepo;
import model.DrugTrie;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

public class AutocompleteServer {

    public static void main(String[] args) throws Exception {
        DrugDataLoader drugLoader = new DrugDataLoader("resources/drugData.json");

        DrugRepo drugRepo = new DrugRepo();
        drugRepo.loadDrugs(drugLoader.getDrugs());

        DrugTrie trie = new DrugTrie();
        trie.loadFromDrugRepo(drugRepo);

        ObjectMapper mapper = new ObjectMapper();

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/api/drugs/search", exchange -> {
            addCorsHeaders(exchange);

            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                exchange.close();
                return;
            }

            if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                sendJson(exchange, 405, "{\"error\":\"Method not allowed\"}");
                return;
            }

            String query = getQueryParam(exchange, "q");
            List<String> results;

            if (query == null || query.trim().isEmpty()) {
                results = Collections.emptyList();
            } else {
                results = trie.searchPrefix(query.trim());
                if (results.size() > 10) {
                    results = results.subList(0, 10);
                }
            }

            String json = mapper.writeValueAsString(results);
            sendJson(exchange, 200, json);
        });

        server.start();
        System.out.println("Autocomplete server running at http://localhost:8080");
    }

    private static void addCorsHeaders(HttpExchange exchange) {
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
    }

    private static void sendJson(HttpExchange exchange, int statusCode, String json) throws IOException {
        byte[] responseBytes = json.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(statusCode, responseBytes.length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(responseBytes);
        }
    }

    private static String getQueryParam(HttpExchange exchange, String key) {
        String query = exchange.getRequestURI().getQuery();
        if (query == null || query.isEmpty()) {
            return null;
        }

        String[] pairs = query.split("&");
        for (String pair : pairs) {
            String[] parts = pair.split("=", 2);
            String paramKey = URLDecoder.decode(parts[0], StandardCharsets.UTF_8);
            String paramValue = parts.length > 1
                    ? URLDecoder.decode(parts[1], StandardCharsets.UTF_8)
                    : "";

            if (paramKey.equals(key)) {
                return paramValue;
            }
        }
        return null;
    }
}