package com.gild.peliculas.peliculas_api.Controller;

import com.gild.peliculas.peliculas_api.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
/**
 * Controlador REST que gestiona la obtención de detalles y videos de películas.
 */
@RestController
@RequestMapping("/api/view")
@CrossOrigin(origins = "*")// Permite solicitudes desde cualquier origen
public class ViewPeliculasController {

    @Autowired
    private DetailsService detailsService;
    /**
     * Endpoint para obtener los detalles de una película por su ID.
     * @param id ID de la película
     * @param type Tipo de contenido (por defecto "movie")
     * @return JSON con los detalles de la película o mensaje de error
     */

    @GetMapping("/details")
    public ResponseEntity<String> getDetails(
            @RequestParam String id,
            @RequestParam(defaultValue = "movie") String type) {

        try {
            String result = detailsService.getMovieDetails(id);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener detalles: " + e.getMessage());
        }
    }

    /**
     * Endpoint para obtener los videos (trailers, clips) de una película.
     * @param id ID de la película
     * @param type Tipo de contenido (por defecto "movie")
     * @return JSON con los videos de la película o mensaje de error
     */
    @GetMapping("/videos")
    public ResponseEntity<String> getVideos(
            @RequestParam String id,
            @RequestParam(defaultValue = "movie") String type) {

        try {
            String result = detailsService.getMovieVideos(id);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener videos: " + e.getMessage());
        }
    }
}