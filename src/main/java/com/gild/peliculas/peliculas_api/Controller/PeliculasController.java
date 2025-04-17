package com.gild.peliculas.peliculas_api.Controller;

import com.gild.peliculas.peliculas_api.service.PeliculasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PeliculasController {

    @Autowired
    private PeliculasService tmdbService;

    @GetMapping("/Ptrending")
    public String getTrendingMovies() {
        try {
            return tmdbService.getTrendingMovies();
        } catch (Exception e) {
            return "Error al obtener películas: " + e.getMessage();
        }
    }
    @GetMapping("/Strending")
    public String getTrendingSeries(){
        try {
            return tmdbService.getTrendSeries();
        } catch (Exception e) {
            return "error al obtener series"+e.getMessage();
        }
    }
    @GetMapping("/Descubrir")
    public String getDescubrir(){
        try {
            return tmdbService.getDiscover();
        }
        catch (Exception e){
           return "error al obtener Descubrir"+e.getMessage();
        }
    }
}
