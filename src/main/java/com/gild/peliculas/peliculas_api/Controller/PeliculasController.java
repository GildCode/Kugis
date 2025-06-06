package com.gild.peliculas.peliculas_api.Controller;

import com.gild.peliculas.peliculas_api.service.PeliculasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * Controlador REST que proporciona endpoints para obtener películas y series populares y recomendadas.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PeliculasController {

    @Autowired
    private PeliculasService tmdbService;
    /**
     * Endpoint para obtener películas en tendencia.
     * @return JSON con películas en tendencia o un mensaje de error
     */
    @GetMapping("/Ptrending")
    public String getTrendingMovies() {
        try {
            return tmdbService.getTrendingMovies();
        } catch (Exception e) {
            return "Error al obtener películas: " + e.getMessage();
        }
    }
    /**
     * Endpoint para obtener series en tendencia.
     * @return JSON con series en tendencia o un mensaje de error
     */

    @GetMapping("/Strending")
    public String getTrendingSeries(){
        try {
            return tmdbService.getTrendSeries();
        } catch (Exception e) {
            return "error al obtener series"+e.getMessage();
        }
    }
    /**
     * Endpoint para obtener contenido recomendado o de descubrimiento.
     * @return JSON con contenido de descubrimiento o un mensaje de error
     */


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
