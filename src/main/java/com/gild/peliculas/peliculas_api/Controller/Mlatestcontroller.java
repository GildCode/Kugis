package com.gild.peliculas.peliculas_api.Controller;

import com.gild.peliculas.peliculas_api.service.MlatestService;
import com.gild.peliculas.peliculas_api.service.PeliculasService;
import com.gild.peliculas.peliculas_api.service.SlatestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Controlador REST que expone endpoints para obtener las películas y series más recientes.
 */
@RestController
@RequestMapping("/api/latest")

public class Mlatestcontroller {
    @Autowired
    private MlatestService mlatestService;
    @Autowired
    private SlatestService slatestService;

    /**
     * Endpoint para obtener las películas más recientes.
     * @return JSON con las películas más recientes o un mensaje de error.
     */
    @GetMapping("/Mlatest")
    public String getMoviesLatest(){
        try {
            return mlatestService.getMovieLatest();
        }catch (Exception e){
            return "Error al obtener películas: " + e.getMessage();

        }
    }
    /**
     * Endpoint para obtener las series más recientes.
     * @return JSON con las series más recientes o un mensaje de error.
     */
    @GetMapping("/Slatest")
    public String getSeriesLatest(){
        try {
            return slatestService.geSeriesLatest();

        }catch (Exception e){
            return "Error al obtener series: " + e.getMessage();
        }
    }

}
