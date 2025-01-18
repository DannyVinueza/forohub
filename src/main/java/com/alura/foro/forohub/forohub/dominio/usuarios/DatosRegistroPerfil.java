package com.alura.foro.forohub.forohub.dominio.usuarios;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroPerfil(@NotBlank String nombre) {
}
