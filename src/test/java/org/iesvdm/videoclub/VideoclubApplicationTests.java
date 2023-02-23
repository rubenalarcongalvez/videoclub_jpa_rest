package org.iesvdm.videoclub;

import jakarta.transaction.Transactional;
import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.repository.CategoriaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@SpringBootTest
class VideoclubApplicationTests {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    void contextLoads() {
    }

//    @Test
//    public void testPelisConteo() {
//        List<Categoria> categorias = this.categoriaRepository.findAll();
//
//        categorias.forEach(categoria -> System.out.println(categoria.getConteoPeliculas()));
//
//    }

}
