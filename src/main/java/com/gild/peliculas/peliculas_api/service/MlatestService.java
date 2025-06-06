package com.gild.peliculas.peliculas_api.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
/**
 * Servicio encargado de obtener las últimas películas en cartelera
 * desde la API de TMDB y devolver las 10 más recientes.
 */

@Service
public class MlatestService {
    private final String URL_API_LATEST="https://api.themoviedb.org/3/movie/now_playing";
    private final OkHttpClient client=new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Token para autenticación con la API de TMDB
    @Value("${tmdb.api.token}")
    private String BEARER_TOKEN;
    // Idioma de respuesta configurado (español, inglés, etc.)
    @Value("${tmdb.api.language1}")
    private String language;
    /**
     * Realiza una petición a TMDB para obtener las películas actualmente en cines,
     * y retorna solo las primeras 10 en formato JSON.
     *
     * @return JSON con las 10 películas más recientes
     * @throws IOException en caso de fallo en la petición o procesamiento del JSON
     */
    public String getMovieLatest() throws IOException {
        Request request = new Request.Builder()
                .url(URL_API_LATEST+"?language="+language)
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
                // Procesar el JSON para devolver solo los primeros 10 resultados
                JsonNode rootNode = objectMapper.readTree(jsonData);

                // Validación de estructura
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



