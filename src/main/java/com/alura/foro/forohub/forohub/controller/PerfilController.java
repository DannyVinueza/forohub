package com.alura.foro.forohub.forohub.controller;

import com.alura.foro.forohub.forohub.dominio.usuarios.DatosDetallePerfil;
import com.alura.foro.forohub.forohub.dominio.usuarios.DatosRegistroPerfil;
import com.alura.foro.forohub.forohub.dominio.usuarios.Perfil;
import com.alura.foro.forohub.forohub.dominio.usuarios.PerfilRepository;
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
@RequestMapping("/perfiles")
@SecurityRequirement(name = "bearer-key")
public class PerfilController {
    @Autowired
    private PerfilRepository perfilRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrarPerfil(@RequestBody @Valid DatosRegistroPerfil datos,
                                          UriComponentsBuilder uriComponentsBuilder){
        var perfil = new Perfil(datos);
        perfilRepository.save(perfil);
        var uri = uriComponentsBuilder.path("/perfiles/{id}").buildAndExpand(perfil.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetallePerfil(perfil));
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetallePerfil>> listar(@PageableDefault(page = 0, size = 10 ) Pageable paginacion){
        var page = perfilRepository.findAll(paginacion).map(DatosDetallePerfil::new);
        return ResponseEntity.ok(page);
    }
}
