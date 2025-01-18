package com.alura.foro.forohub.forohub.dominio.topico;

public record DatosActualizarTopico(
        String titulo,
        String mensaje,
        Estados status,
        Long autor,
        Long curso) {

}
