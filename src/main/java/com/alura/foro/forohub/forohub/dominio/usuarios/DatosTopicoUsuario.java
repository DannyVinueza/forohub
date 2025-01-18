package com.alura.foro.forohub.forohub.dominio.usuarios;

public record DatosTopicoUsuario (
        Long id,
        String nombre,
        String email,
        Perfil perfil
){
    public DatosTopicoUsuario(Usuario datos){
        this(datos.getId(), datos.getNombre(), datos.getEmail(), datos.getPerfil());
    }
}
