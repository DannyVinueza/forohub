package com.alura.foro.forohub.forohub.controller;

import com.alura.foro.forohub.forohub.dominio.usuarios.DatosLoginUsuario;
import com.alura.foro.forohub.forohub.dominio.usuarios.Usuario;
import com.alura.foro.forohub.forohub.infra.errores.ErrorResponse;
import com.alura.foro.forohub.forohub.infra.security.DatosJWTToken;
import com.alura.foro.forohub.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosLoginUsuario datosLoginUsuario){
        try {
            Authentication authToken = new UsernamePasswordAuthenticationToken(datosLoginUsuario.login(),
                    datosLoginUsuario.clave());
            var usuarioAutenticado = authenticationManager.authenticate(authToken);
            var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
            return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
        }catch (BadCredentialsException e) {
            return new ResponseEntity<>(new ErrorResponse("Credenciales incorrectas", e.getMessage()), HttpStatus.UNAUTHORIZED);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(new ErrorResponse("Usuario no encontrado", e.getMessage()), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("Error en el servidor", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
