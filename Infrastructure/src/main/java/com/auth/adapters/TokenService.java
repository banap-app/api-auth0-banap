package com.auth.adapters;

import auth.com.domain.domain.user.User;
import com.auth.application.Adapters.TokenAdapter;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class TokenService implements TokenAdapter {
    @Value("${jwt.secret}")
    private String secret;

    @Override
    public String generateToken(User user, Instant issuedAt, Instant expiresAt) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer("auth-service-banap")
                .withSubject(user.getEmail().getValue())
                .withSubject(user.getId().getValue())
                .withClaim("typeUserName",user.getTypeUser().getName())
                .withClaim("typeUserId",String.valueOf(user.getTypeUser().getId()))
                .withIssuedAt(Date.from(issuedAt))
                .withExpiresAt(Date.from(expiresAt))
                .sign(algorithm);
    }

    @Override
    public Boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWT.require(algorithm)
                    .withIssuer("auth-service-banap") // Validação opcional do emissor
                    .build()
                    .verify(token); // Se o token for inválido, uma exceção será lançada.

            return true;
        } catch (Exception e) {
            return false; // Token inválido ou expirado.
        }
    }

    @Override
    public String getSubject(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
                .withIssuer("auth-service-banap")
                .build()
                .verify(token)
                .getSubject();
    }
}
