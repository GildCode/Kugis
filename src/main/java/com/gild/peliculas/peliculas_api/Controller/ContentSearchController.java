package com.gild.peliculas.peliculas_api.Controller;

import com.gild.peliculas.peliculas_api.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para manejar la búsqueda de contenido (películas o series) por nombre.
 */

@RestController
@RequestMapping("/api/search")
@CrossOrigin(origins = "*")// Permite solicitudes desde cualquier origen

/**
 * Endpoint para buscar contenido por nombre.
 * @param nameContent nombre del contenido a buscar
 * @return JSON con los resultados de la búsqueda o un mensaje de error
 */
public class ContentSearchController {
    @Autowired
    private SearchService searchContent;
    @GetMapping("/searchcontent")
    private ResponseEntity<String> Search (@RequestParam String nameContent){
        try {
            String resultado= searchContent.searchContent(nameContent);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }


    }




}
