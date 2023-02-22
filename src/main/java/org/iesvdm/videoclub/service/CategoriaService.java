package org.iesvdm.videoclub.service;

import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.exception.CategoriaNotFoundException;
import org.iesvdm.videoclub.repository.CategoriaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> all() {
        return this.categoriaRepository.findAll();
    }

    public Categoria save(Categoria categoria) {
        return this.categoriaRepository.save(categoria);
    }

    public Categoria one(Long id) {
        return this.categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    public Categoria replace(Long id, Categoria categoria) {
        categoria.setUltimaActualizacion(new Date());
        return this.categoriaRepository.findById(id).map( p -> (id.equals(categoria.getId())  ?
                                                            this.categoriaRepository.save(categoria) : null))
                .orElseThrow(() -> new CategoriaNotFoundException(id));

    }

    public void delete(Long id) {
        this.categoriaRepository.findById(id).map(p -> {this.categoriaRepository.delete(p);
                                                        return p;})
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    public List<Categoria> allByQueryFiltersStream(Optional<String> optionalBuscar, Optional<String> optionalOrdenar) {

        if (optionalOrdenar.equals(Optional.of("asc"))) {
            return this.categoriaRepository.findAllByNombreContainingIgnoreCaseOrderByNombreAsc(optionalBuscar.get());
        } else {
            return this.categoriaRepository.findAllByNombreContainingIgnoreCaseOrderByNombreDesc(optionalBuscar.get());
        }

    }

    //Paginación
    public Map<String, Object> all(int pagina, int tamanio) {
        Pageable paginado = PageRequest.of(pagina, tamanio, Sort.by("id"));

        Page<Categoria> pageAll = this.categoriaRepository.findAll(paginado);

        Map<String, Object> response = new HashMap<>();

        response.put("categorias", pageAll.getContent());
        response.put("currentPage", pageAll.getNumber());
        response.put("totalItems", pageAll.getTotalElements());
        response.put("totalPages", pageAll.getTotalPages());

        return response;
    }

}
