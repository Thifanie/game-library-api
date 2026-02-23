package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.Game;
import service.GameService;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class GameHandler implements HttpHandler {
    private final GameService service;
    private final ObjectMapper mapper = new ObjectMapper();

    public GameHandler(GameService service) {
        this.service = service;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        try {
            switch (method) {
                case "GET" -> handleGet(exchange, path);
//                case "POST" -> handlePost(exchange);
//                case "PUT" -> handlePut(exchange, path);
//                case "DELETE" -> handleDelete(exchange, path);
                default -> sendResponse(exchange, 405, "Method Not Allowed");
            }
        } catch (IllegalArgumentException e) {
            sendResponse(exchange, 400, e.getMessage());
        } catch (RuntimeException e) {
            sendResponse(exchange, 404, e.getMessage());
        } catch (Exception e) {
            sendResponse(exchange, 500, "Internal Server Error");
        }
    }

    private void handleGet(HttpExchange exchange, String path) throws IOException {
        if (path.matches("/games/\\d+")) {
//            \d+ est l'expression régulière qui signifie un ou plusieurs chiffres et un \ est
//            ajouté pour échapper le backslash
            Long id = extractId(path);
            Game game = service.getById(id);
            sendResponse(exchange, 200, mapper.writeValueAsString(game));
        } else if (path.equals("/games")) {
            List<Game> games = service.getAll();
            sendResponse(exchange, 200, mapper.writeValueAsString(games));
        } else {
            sendResponse(exchange, 404, "Not Found");
        }
    }

    private Long extractId(String path) {
        return Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
//        lastIndexOf va chercher la position du dernier slash dans le String path.
//        parseLong va convertir la sous-chaîne extraite en Long, ici l'id après le dernier slash
    }

    private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.getResponseHeaders().add("Content-Type", "application/json");
//        On indique que le corps de la réponse est du JSON.
        byte[] bytes = response.getBytes();
//        getBytes convertit la réponse en tableau d'octets car HttpExchange envoie les données en binaire
        exchange.sendResponseHeaders(statusCode, bytes.length);
//        La longueur du corps de la réponse est indiquée pour que le client sache quand la réponse est terminée.
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
//            getResponseBody donne le flux de sortie vers le client et on écrit le tableau d'octets dans ce flux.
//            Avec le try, le flux est fermé automatiquement après écriture.
        }
    }
}
