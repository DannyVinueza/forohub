package com.alura.foro.forohub.forohub.dominio.usuarios;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Entity(name= "perfil")
@Table(name = "perfiles")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String nombre;

    public Perfil(@Valid DatosRegistroPerfil datos){
        this.nombre = datos.nombre();
    }

    public Perfil() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
