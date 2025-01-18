package com.alura.foro.forohub.forohub.infra.security;

import com.alura.foro.forohub.forohub.dominio.usuarios.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("foro hub")
                    .withSubject(usuario.getEmail())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("No se pudo generar el token");
        }
    }

    public String getSubject(String token){
        if(token == null){
            throw new RuntimeException("");
        }

        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("foro hub")
                    // reusable verifier instance
                    .build()
                    .verify(token);
            verifier.getSubject();

        } catch (JWTVerificationException exception){
            // Invalid signature/claims
            System.out.println(exception.toString());
        }
        if(verifier.getSubject() == null){
            throw new RuntimeException("Verifie Invalido");
        }
        return verifier.getSubject();

    }

    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
