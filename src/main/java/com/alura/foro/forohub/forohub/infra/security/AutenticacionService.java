package com.alura.foro.forohub.forohub.infra.security;

import com.alura.foro.forohub.forohub.dominio.usuarios.Usuario;
import com.alura.foro.forohub.forohub.dominio.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
       var usuario = usuarioRepository.findByEmail(username);

       if(usuario == null){
           throw new UsernameNotFoundException("Usuario no encontrado con el nombre: " + username);
       }

       return usuario;
    }
}
