package com.gild.peliculas.peliculas_api.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class SdetailsService {
    private final OkHttpClient client = new OkHttpClient();
    // Token para autenticación con la API de TMDB
    @Value("${tmdb.api.token}")
    private String BEARER_TOKEN;
    // Idioma
    @Value("${tmdb.api.language1}")
    private String language1;
    @Value("${tmdb.api.language2}")
    private String language2;
    // Realiza la peticion para obtener los detalles de las series
    public String getSeriesDetails(String idMovie) throws IOException {
        String url = String.format(
                "https://api.themoviedb.org/3/tv/%s?language=%s",
                idMovie,
                language2
        );

        return executeRequest(url);
    }
    // Realiza la peticion para obtener trailer  de la pelicula si lo hay en youtube
    public String getSerieseVideos(String idMovie) throws IOException {
        String url = String.format(
                "https://api.themoviedb.org/3/tv/%s/videos?language=%s",
                idMovie,
                language2
        );

        return executeRequest(url);
    }
    //Realiza una solicitud HTTP GET a una URL específica y devuelve la respuesta como String
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
