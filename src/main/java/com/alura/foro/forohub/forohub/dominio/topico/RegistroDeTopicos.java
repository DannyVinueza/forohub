package com.alura.foro.forohub.forohub.dominio.topico;

import com.alura.foro.forohub.forohub.dominio.curso.Curso;
import com.alura.foro.forohub.forohub.dominio.curso.CursoRepository;
import com.alura.foro.forohub.forohub.dominio.usuarios.Usuario;
import com.alura.foro.forohub.forohub.dominio.usuarios.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroDeTopicos {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public DatosDetalleTopico registrar(DatosRegistroTopico datosRegistroTopico){
        boolean existeDuplicado = topicoRepository.existsByTituloAndMensaje(datosRegistroTopico.titulo(), datosRegistroTopico.mensaje());
        if(existeDuplicado){
            throw new IllegalArgumentException("YA existe un tópico con el mismo titulo");
        }

        Usuario autor = usuarioRepository.findById(datosRegistroTopico.autor())
                .orElseThrow(() -> new EntityNotFoundException("Autor No encontrado"));

        Curso curso = cursoRepository.findById(datosRegistroTopico.curso())
                .orElseThrow(() -> new EntityNotFoundException("CUrso no encontrado"));

        Topico topico = new Topico(datosRegistroTopico, autor, curso);
        topicoRepository.save(topico);
        return new DatosDetalleTopico(topico);
    }
}
