package com.alura.foro.forohub.forohub.controller;

import com.alura.foro.forohub.forohub.dominio.usuarios.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PerfilRepository perfilRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroUsuario dataUsuario, UriComponentsBuilder uriComponentsBuilder){
        var perfil = perfilRepository.findById(dataUsuario.idPerfil())
                .orElseThrow(() -> new RuntimeException("Lo sentimos, perfil no encontrado"));

        var usuario = new Usuario(dataUsuario.nombre(), dataUsuario.email(), dataUsuario.contrasena(), perfil);

        usuarioRepository.save(usuario);

        var datosDetalleUsuario = new DatosDetalleUsuario(usuario);
        var uri = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(datosDetalleUsuario);
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalleUsuario>> listarUsuarios(@PageableDefault(page = 0, size = 10)Pageable pageable){
        var paginacionUsuarios = usuarioRepository.findAll(pageable).map(DatosDetalleUsuario::new);
        return ResponseEntity.ok(paginacionUsuarios);
    }

}
