package com.alura.foro.forohub.forohub.dominio.topico;

import com.alura.foro.forohub.forohub.dominio.curso.Curso;
import com.alura.foro.forohub.forohub.dominio.usuarios.DatosTopicoUsuario;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,

        String titulo,

        String mensaje,

        LocalDateTime fecha_creacion,

        Estados status,

        DatosTopicoUsuario autor,

        Curso curso
) {
    public DatosDetalleTopico(Topico datos){
        this(datos.getId(), datos.getTitulo(), datos.getMensaje(), datos.getFecha_creacion(), datos.getStatus(),
                new DatosTopicoUsuario(datos.getAutor().getId(), datos.getAutor().getNombre(), datos.getAutor().getEmail(),
                        datos.getAutor().getPerfil()), datos.getCurso());
    }
}
