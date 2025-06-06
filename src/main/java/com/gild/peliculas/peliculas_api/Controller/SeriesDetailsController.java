package com.gild.peliculas.peliculas_api.Controller;


import com.gild.peliculas.peliculas_api.service.SdetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
/**
 * Controlador REST que gestiona la obtención de detalles y videos de series.
 */
@RestController
@RequestMapping("/api/view")
@CrossOrigin(origins = "*")// Permite solicitudes desde cualquier origen

public class SeriesDetailsController {

    @Autowired
    private SdetailsService sdetailsService;
    /**
     * Endpoint para obtener los detalles de una serie por su ID.
     * @param id ID de la serie
     * @return JSON con los detalles o un mensaje de error
     */
    @GetMapping("/Sdetails")
    public ResponseEntity<String> getDetails(
            @RequestParam String id) {

        try {
            String result = sdetailsService.getSeriesDetails(id);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener detalles: " + e.getMessage());
        }
    }
    /**
     * Endpoint para obtener los videos (trailers, teasers) de una serie.
     * @param id ID de la serie
     * @param type tipo de contenido (por defecto "movie")
     * @return JSON con los videos o un mensaje de error
     */

    @GetMapping("/Svideos")
    public ResponseEntity<String> getVideos(
            @RequestParam String id,
            @RequestParam(defaultValue = "movie") String type) {

        try {
            String result = sdetailsService.getSerieseVideos(id);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener videos: " + e.getMessage());
        }
    }
}
