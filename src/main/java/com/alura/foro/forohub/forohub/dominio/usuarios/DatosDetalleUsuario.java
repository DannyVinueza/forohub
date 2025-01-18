package com.alura.foro.forohub.forohub.dominio.usuarios;

public record DatosDetalleUsuario (Long id, String nombre, String email, String contrasena, Long idPerfil){
    public DatosDetalleUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getContrasena(), usuario.getPerfil().getId());
    }
}
