package org.example.dio.molniya.security;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class JwtConfiguration {
    @Value("${molniya.security.hmacsecretkey}")
    private String hmacsecretkey;

    @Bean
    public JwtBuilder getJwtBuilder() {
        return Jwts.builder().signWith(
                Keys.hmacShaKeyFor(Decoders.BASE64.decode(hmacsecretkey))
        );
    }

    @Bean
    public JwtParser getJwtParser() {
        return Jwts.parserBuilder()
                .setSigningKey(Decoders.BASE64.decode(hmacsecretkey))
                .build();
    }
}
