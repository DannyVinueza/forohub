package com.alura.foro.forohub.forohub.dominio.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,

        @NotNull
        Long autor,
        @NotNull
        Long curso
) {
}
