package com.auth.adapters;

import auth.com.domain.domain.user.User;
import com.auth.application.Adapters.TokenAdapter;
import com.auth.utils.SecretManagerUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;

import java.time.Instant;
import java.util.Date;


@Service
public class TokenService implements TokenAdapter {

    private SecretManagerUtil secretManagerUtil;

    @Value("${secretmanager.tokenSecret}")
    private String tokenSecret;

    @Value("${secretmanager.region}")
    private String region;

    public TokenService(SecretManagerUtil secretManagerUtil) {
        this.secretManagerUtil = secretManagerUtil;
    }

    @Override
    public String generateToken(User user, Instant issuedAt, Instant expiresAt) {
        final String secretToken = secretManagerUtil.getSecret(tokenSecret, Region.of(region));
        Algorithm algorithm = Algorithm.HMAC256(secretToken);
        return JWT.create()
                .withIssuer("auth-service-banap")
                .withSubject(user.getEmail())
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
            String secretToken = secretManagerUtil.getSecret(tokenSecret, Region.of(region));
            Algorithm algorithm = Algorithm.HMAC256(secretToken);
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
        String secretToken = secretManagerUtil.getSecret(tokenSecret, Region.of(region));
        Algorithm algorithm = Algorithm.HMAC256(secretToken);
        return JWT.require(algorithm)
                .withIssuer("auth-service-banap")
                .build()
                .verify(token)
                .getSubject();
    }
}

