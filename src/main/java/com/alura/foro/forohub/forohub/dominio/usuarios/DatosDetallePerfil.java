package com.alura.foro.forohub.forohub.dominio.usuarios;

public record DatosDetallePerfil (Long id, String nombre){
    public DatosDetallePerfil(Perfil perfil){
        this(perfil.getId(), perfil.getNombre());
    }
}
