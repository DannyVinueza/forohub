package com.alura.foro.forohub.forohub.dominio.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record DatosRegistroUsuario(@NotBlank String nombre,
                                   @NotBlank
                                   @Email
                                   String email,
                                   @NotBlank String contrasena,
                                   @NotBlank
                                   @Positive
                                   Long idPerfil) {
}
