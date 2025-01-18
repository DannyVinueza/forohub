package com.alura.foro.forohub.forohub.controller;

import com.alura.foro.forohub.forohub.dominio.curso.Curso;
import com.alura.foro.forohub.forohub.dominio.curso.CursoRepository;
import com.alura.foro.forohub.forohub.dominio.curso.DatosDetalleCurso;
import com.alura.foro.forohub.forohub.dominio.curso.DatosRegistroCurso;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroCurso datos,
                                    UriComponentsBuilder uriComponentsBuilder){
        var curso = new Curso(datos);
        cursoRepository.save(curso);

        var datosDetalleCurso = new DatosDetalleCurso(curso);
        var uri = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(datosDetalleCurso);
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalleCurso>> listar(@PageableDefault(page = 0, size = 10) Pageable paginacion){
        var page = cursoRepository.findAll(paginacion).map(DatosDetalleCurso::new);
        return ResponseEntity.ok(page);
    }
}
