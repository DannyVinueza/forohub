package com.alura.foro.forohub.forohub.dominio.respuesta;

import com.alura.foro.forohub.forohub.dominio.topico.DatosDetalleTopico;
import com.alura.foro.forohub.forohub.dominio.usuarios.DatosTopicoUsuario;

import java.time.LocalDateTime;

public record DatosDetalleRespuesta(Long id,
                                    String mensaje,
                                    DatosDetalleTopico idTopico,
                                    LocalDateTime fecha,
                                    DatosTopicoUsuario idAutor,
                                    boolean solucion) {
    public DatosDetalleRespuesta(Respuesta datos){
        this(
                datos.getId(),
                datos.getMensaje(),
                new DatosDetalleTopico(
                        datos.getTopico().getId(),
                        datos.getTopico().getTitulo(),
                        datos.getTopico().getMensaje(),
                        datos.getTopico().getFecha_creacion(),
                        datos.getTopico().getStatus(),
                        new DatosTopicoUsuario(
                                datos.getTopico().getAutor().getId(),
                                datos.getTopico().getAutor().getNombre(),
                                datos.getTopico().getAutor().getEmail(),
                                datos.getTopico().getAutor().getPerfil()
                        ),
                        datos.getTopico().getCurso()
                ),
                datos.getFecha_creacion(),
                new DatosTopicoUsuario(
                        datos.getUsuario().getId(),
                        datos.getUsuario().getNombre(),
                        datos.getUsuario().getEmail(),
                        datos.getUsuario().getPerfil()
                ),
                datos.isSolucion()
        );
    }
}
