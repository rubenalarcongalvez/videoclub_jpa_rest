package org.iesvdm.videoclub.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categoria")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private long id;

    private String nombre;

    @ManyToMany(
            mappedBy = "categorias", fetch = FetchType.EAGER)
    @JsonIgnore
    Set<Pelicula> peliculas = new HashSet<>();

    @Column(name = "ultima_actualizacion")
    @JsonFormat(pattern = "yyyy-MM-dd-HH:mm:ss",  shape = JsonFormat.Shape.STRING)
    private Date ultimaActualizacion;

    public int getConteoPeliculas() {
        return this.peliculas.size();
    }

//    private int conteoPeliculas;

//    public Categoria(long id, String nombre, Set<Pelicula> peliculas, Date ultimaActualizacion, int conteoPeliculas) {
//        this.id = id;
//        this.nombre = nombre;
//        this.peliculas = peliculas;
//        this.ultimaActualizacion = ultimaActualizacion;
//        this.conteoPeliculas = peliculas.size();
//    }
}