package org.iesvdm.videoclub.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

//    Ruta sin rutas que contengan esos parámetros
    @GetMapping(value = {"", "/"}, params = {"!buscar", "!ordenar", "!pagina", "!tamanio"})
    public List<Categoria> all() {
        log.info("Accediendo a todas las categorias");
        return this.categoriaService.all();
    }

    //Ruta sin exclusión de los params por negación
    @GetMapping(value = {"", "/"}, params = {"!pagina", "!tamanio"})
    public List<Categoria> all(@RequestParam("buscar") Optional<String> buscarOptional, @RequestParam("ordenar") Optional<String> ordenarOptional) {
        log.info("Accediendo a todas las categorias con filtro buscar: %s y ordenar: %o",
                buscarOptional.orElse("VOID"),
                ordenarOptional.orElse("VOID"));

        return this.categoriaService.allByQueryFiltersStream(buscarOptional, ordenarOptional);
    }

    @GetMapping(value = {"", "/"}, params = {"!buscar", "!ordenar"})
    public ResponseEntity<Map<String, Object>> all(@RequestParam("pagina") int pagina, @RequestParam("tamanio") int tamanio) {
        log.info("Accediendo a todas las categorias con paginación");

        Map<String, Object> responseall = this.categoriaService.all(pagina, tamanio);

        return ResponseEntity.ok(responseall);
    }

    @PostMapping({"", "/"})
    public Categoria newCategoria(@RequestBody Categoria categoria) {
        categoria.setUltimaActualizacion(new Date());
        return this.categoriaService.save(categoria);
    }

    @GetMapping("/{id}")
    public Categoria one(@PathVariable("id") Long id) {
        return this.categoriaService.one(id);
    }

    @PutMapping("/{id}")
    public Categoria replaceCategoria(@PathVariable("id") Long id, @RequestBody Categoria categoria) {
        return this.categoriaService.replace(id, categoria);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCategoria(@PathVariable("id") Long id) {
        this.categoriaService.delete(id);
    }


}
