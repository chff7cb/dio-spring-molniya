package org.example.dio.molniya.controller;

import com.google.gson.Gson;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import jakarta.persistence.Entity;
import org.example.dio.molniya.domain.LoginRequest;
import org.example.dio.molniya.domain.Usuario;
import org.example.dio.molniya.security.UsuarioDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.sql.Date;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final UsuarioDetailsService detailsService;

    private final JwtBuilder jwtBuilder;

    public LoginController(UsuarioDetailsService detailsService, JwtBuilder jwtBuilder) {
        this.detailsService = detailsService;
        this.jwtBuilder = jwtBuilder;
    }

    @PostMapping
    public String /**/ login(@RequestBody LoginRequest req) {
        UserDetails user = detailsService.loadUserByUsername(req.usuario);
        return jwtBuilder
                .setSubject(user.getUsername())
                .setExpiration(Date.from(Instant.now().plusSeconds(24*60*60)))
                .compact();
    }
}
