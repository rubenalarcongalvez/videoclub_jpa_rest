package org.iesvdm.videoclub.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.service.CategoriaService;
import org.iesvdm.videoclub.service.PeliculaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/peliculas")
public class PeliculaController {
    private final PeliculaService peliculaService;
    private final CategoriaService categoriaService;

    public PeliculaController(PeliculaService peliculaService, CategoriaService categoriaService) {
        this.peliculaService = peliculaService; this.categoriaService = categoriaService;
    }

    @GetMapping({"","/"})
    public List<Pelicula> all() {
        log.info("Accediendo a todas las películas");
        return this.peliculaService.all();
    }

    @PostMapping({"","/"})
    public Pelicula newPelicula(@RequestBody Pelicula pelicula) {
//        long[] categorias = pelicula.getCategorias().stream().mapToLong(Categoria::getId).toArray();
//
//        for (int i = 0; i < categorias.length; i++) {
//            this.categoriaService.one(categorias[i]).setConteoPeliculas(this.categoriaService.one(categorias[i]).getConteoPeliculas() + 1);
//        }

        pelicula.setUltimaActualizacion(new Date());
        return this.peliculaService.save(pelicula);
    }

    @GetMapping("/{id}")
    public Pelicula one(@PathVariable("id") Long id) {
        return this.peliculaService.one(id);
    }

    @PutMapping("/{id}")
    public Pelicula replacePelicula(@PathVariable("id") Long id, @RequestBody Pelicula pelicula) {
        pelicula.setUltimaActualizacion(new Date());
        return this.peliculaService.replace(id, pelicula);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePelicula(@PathVariable("id") Long id) {
        this.peliculaService.delete(id);
    }


}
