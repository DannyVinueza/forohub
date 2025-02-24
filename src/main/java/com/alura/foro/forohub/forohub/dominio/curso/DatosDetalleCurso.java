package com.alura.foro.forohub.forohub.dominio.curso;

public record DatosDetalleCurso (Long id,
                                 String nombre,
                                 Categoria categoria){
    public DatosDetalleCurso(Curso datos){
        this(datos.getId(), datos.getNombre(), datos.getCategoria());
    }
}
