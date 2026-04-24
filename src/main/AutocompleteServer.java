package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import model.DrugDataLoader;
import model.DrugRepo;
import model.DrugTrie;
import model.Patient;
import model.PatientContraindicationResult;
import model.PatientDataLoader;
import model.PatientRepo;
import service.DrugContraindicationService;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

/**
 * java http server used to connect the frontend to the backend of our project
 * 
 * this server exposes the api endpoints for drug autocomplete, including patient lookup,
 * and checking for contraindication.
 * @param args
 * @throws Exception
 */
public class AutocompleteServer {

    public static void main(String[] args) throws Exception {
        DrugDataLoader drugLoader = new DrugDataLoader("resources/drugData.json");

        DrugRepo drugRepo = new DrugRepo();
        drugRepo.loadDrugs(drugLoader.getDrugs());

        DrugTrie trie = new DrugTrie();
        trie.loadFromDrugRepo(drugRepo);

        ObjectMapper mapper = new ObjectMapper();

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);        
        
        /* ======== Drug Autocomplete API Endpoint ======== */
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
        
        
     // Load patient data from JSON and populate patient repository
        PatientDataLoader patientLoader = new PatientDataLoader("resources/patientData.json");
        PatientRepo patientRepo = new PatientRepo();
        patientRepo.loadPatients(patientLoader.getPatients());
        // Initialise contraindication service with drug data (Used to check new drug against patient's existing medications)
        DrugContraindicationService contraindicationService = 
                new DrugContraindicationService(drugLoader.getDrugs());
        
        server.createContext("/api/patients", exchange -> {
            addCorsHeaders(exchange);
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                exchange.close();
                return;
            }
            //only GET requests allowed
            if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                sendJson(exchange, 405, "{\"error\":\"Method not allowed\"}");
                return;
            }
            //get name or ID query parameter (ID is optional, but name is required for lookup)
            String name = getQueryParam(exchange, "name");
            String id   = getQueryParam(exchange, "id");
            String q = getQueryParam(exchange, "q");
            Patient patient = null;
            if (q != null && !q.trim().isEmpty()) {
                //try ID first, then name
                patient = patientRepo.getPatientById(q);
                if (patient == null) {
                    patient = patientRepo.getPatientByName(q);
                }
            } 
            else if (name != null && !name.trim().isEmpty()) {
                //look up by name
                patient = patientRepo.getPatientByName(name);
            } else if (id != null && !id.trim().isEmpty()) {
                //look up by ID
                patient = patientRepo.getPatientById(id);
            } else {
                sendJson(exchange, 400, "{\"error\":\"Provide either name or id parameter\"}");
                return;
            }
			if (patient == null) {
				sendJson(exchange, 404, "{\"error\":\"Patient not found\"}");
				return;
			}
			
         //convert patient object to JSON and send response
            String json = mapper.writeValueAsString(patient);
            sendJson(exchange, 200, json);
        });
        
        server.createContext("/api/contraindications", exchange -> {
            addCorsHeaders(exchange);
            
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                exchange.close();
                return;
            }
            
            if(!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                sendJson(exchange, 405, "{\"error\":\"Method not allowed\"}");
                return;
            }
            
            //get the drug name being prescribed
            String drugName = getQueryParam(exchange, "drug");
            //get patients's curent medications as comma-separated string
            String patientMedsParam = getQueryParam(exchange, "patientMeds");
            if (drugName == null || drugName.trim().isEmpty()) {
                sendJson(exchange, 400, "{\"error\":\"Missing 'drug' parameter\"}");
                return;
            }
            //convert comma-separated string to List<String>
            List<String> patientMeds = patientMedsParam == null || patientMedsParam.trim().isEmpty()
                    ? Collections.emptyList()
                    : List.of(patientMedsParam.split(","));
            //check new drug against patient's medications
            PatientContraindicationResult result =
                    contraindicationService.checkAgainstPatient(drugName, patientMeds);
            
            String json = mapper.writeValueAsString(result);
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