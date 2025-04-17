package com.gild.peliculas.peliculas_api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SearchService {
    private  final String URL_API_SEARCH_MOVIE="https://api.themoviedb.org/3/search/movie";

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Value("${tmdb.api.token}")
    private String BEARER_TOKEN;
    @Value("${tmdb.api.key}")
    private String API_KEY;

    public String searchContent(String nameContent) throws IOException {
        HttpUrl url=HttpUrl.parse(URL_API_SEARCH_MOVIE).newBuilder()
                .addQueryParameter("query",nameContent)
                .addQueryParameter("include_adult","false")
                .addQueryParameter("language","es-ES")
                .addQueryParameter("page","1")
                .build();
        OkHttpClient client = new OkHttpClient();

        Request request=new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept","application/json")
                .addHeader("Authorization","Bearer "+BEARER_TOKEN)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Error al buscar la película: " + response);
            }

            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }



}
