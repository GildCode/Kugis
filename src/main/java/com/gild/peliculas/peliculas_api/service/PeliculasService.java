package com.gild.peliculas.peliculas_api.service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PeliculasService {

    private final OkHttpClient client = new OkHttpClient();
    private final String API_URL_TPeliculas = "https://api.themoviedb.org/3/trending/movie/day?language=es-ES";
    private final String API_URL_TSeries = "https://api.themoviedb.org/3/tv/popular?language=es-ES&page=1";
    private final String API_URL_Descubrir="https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc";
    private final ObjectMapper objectMapper = new ObjectMapper();
    // Token para autenticación con la API de TMDB
    @Value("${tmdb.api.token}")
    private String BEARER_TOKEN;
    /**
     * Realiza una petición a TMDB para obtener las sugeridas segun la API ,
     * y retorna  en formato JSON.
     *
     * @return JSON con las 10 películas más recientes
     * @throws IOException en caso de fallo en la petición o procesamiento del JSON
     */
    public String getTrendingMovies() throws Exception {
        Request request = new Request.Builder()
                .url(API_URL_TPeliculas)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer " + BEARER_TOKEN)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Error: " + response);
            }

            String jsonData = response.body().string();

            try {
                // Parsear respuesta JSON
                JsonNode rootNode = objectMapper.readTree(jsonData);

                // Verificar si existe el campo results
                if (!rootNode.has("results")) {
                    throw new RuntimeException("La respuesta no contiene la clave 'results'");
                }

                // Obtener el array de resultados
                JsonNode resultsNode = rootNode.get("results");

                // Crear un nuevo ArrayNode para guardar las primeras 10 películas
                ArrayNode top10Node = objectMapper.createArrayNode();

                // Añadir solo las primeras 10 películas
                int count = 0;
                for (JsonNode movie : resultsNode) {
                    if (count >= 10) break;
                    top10Node.add(movie);
                    count++;
                }

                // Devolver el JSON como string
                return objectMapper.writeValueAsString(top10Node);
            } catch (IOException e) {
                throw new RuntimeException("Error al procesar JSON: " + e.getMessage(), e);
            }
        }
    }

    public String getTrendSeries() throws Exception {
        Request request = new Request.Builder()
                .url(API_URL_TSeries)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer " + BEARER_TOKEN)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Error: " + response);
            }

            String jsonData = response.body().string();

            try {
                // Parsear respuesta JSON
                JsonNode rootNode = objectMapper.readTree(jsonData);

                // Verificar si existe el campo results
                if (!rootNode.has("results")) {
                    throw new RuntimeException("La respuesta no contiene la clave 'results'");
                }

                // Obtener el array de resultados
                JsonNode resultsNode = rootNode.get("results");

                // Crear un nuevo ArrayNode para guardar las primeras 10 películas
                ArrayNode top10Node = objectMapper.createArrayNode();

                // Añadir solo las primeras 10 películas
                int count = 0;
                for (JsonNode movie : resultsNode) {
                    if (count >= 10) break;
                    top10Node.add(movie);
                    count++;
                }

                // Devolver el JSON como string
                return objectMapper.writeValueAsString(top10Node);
            } catch (IOException e) {
                throw new RuntimeException("Error al procesar JSON: " + e.getMessage(), e);
            }
        }
    }




    public String getDiscover() throws IOException {
        Request request = new Request.Builder()
                .url(API_URL_Descubrir)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer " + BEARER_TOKEN)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Error: " + response);
            }

            String jsonData = response.body().string();

            try {
                // Parsear respuesta JSON
                JsonNode rootNode = objectMapper.readTree(jsonData);

                // Verificar si existe el campo results
                if (!rootNode.has("results")) {
                    throw new RuntimeException("La respuesta no contiene la clave 'results'");
                }

                // Obtener el array de resultados
                JsonNode resultsNode = rootNode.get("results");

                // Crear un nuevo ArrayNode para guardar las primeras 10 películas
                ArrayNode top10Node = objectMapper.createArrayNode();

                // Añadir solo las primeras 10 películas
                int count = 0;
                for (JsonNode movie : resultsNode) {
                    if (count >= 10) break;
                    top10Node.add(movie);
                    count++;
                }

                // Devolver el JSON como string
                return objectMapper.writeValueAsString(top10Node);
            } catch (IOException e) {
                throw new RuntimeException("Error al procesar JSON: " + e.getMessage(), e);
            }
        }


    }



}
