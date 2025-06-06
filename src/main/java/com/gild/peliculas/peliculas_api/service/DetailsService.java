package com.gild.peliculas.peliculas_api.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
/**
 * Servicio que se encarga de consumir la API de TMDB para obtener
 * detalles y videos de una película específica.
 */

@Service
public class DetailsService {

    private final OkHttpClient client = new OkHttpClient();
    // Token de autorización para acceder a la API de TMDB
    @Value("${tmdb.api.token}")
    private String BEARER_TOKEN;
    // Idioma configurado para la consulta a TMDB
    @Value("${tmdb.api.language2}")
    private String language;
    /**
     * Obtiene los detalles de una película específica usando su ID.
     * @param idMovie ID de la película en TMDB
     * @return JSON con los detalles de la película
     * @throws IOException en caso de error al realizar la petición
     */
    public String getMovieDetails(String idMovie) throws IOException {
        String url = String.format(
                "https://api.themoviedb.org/3/movie/%s?language=%s",
                idMovie,
                language
        );

        return executeRequest(url);
    }
    /**
     * Obtiene los videos (trailers, clips, etc.) de una película específica.
     * @param idMovie ID de la película en TMDB
     * @return JSON con los videos de la película
     * @throws IOException en caso de error al realizar la petición
     */
    public String getMovieVideos(String idMovie) throws IOException {
        String url = String.format(
                "https://api.themoviedb.org/3/movie/%s/videos?language=%s",
                idMovie,
                language
        );

        return executeRequest(url);
    }
    /**
     * Método privado para ejecutar peticiones HTTP GET a la API de TMDB.
     * @param url URL de la API a consultar
     * @return Respuesta en formato JSON como String
     * @throws IOException si la respuesta no es exitosa o hay error de conexión
     */

    private String executeRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer " + BEARER_TOKEN)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Error en la petición: " + response.code() + " - " + response.message());
            }
            return response.body().string();
        }
    }
}