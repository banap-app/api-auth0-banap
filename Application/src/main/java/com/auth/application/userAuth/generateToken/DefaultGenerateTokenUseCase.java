package com.auth.application.userAuth.generateToken;

import auth.com.domain.domain.exceptions.DomainException;
import auth.com.domain.domain.user.Email;
import auth.com.domain.domain.user.User;
import auth.com.domain.domain.user.UserGateway;
import auth.com.domain.domain.validation.Error;
import com.auth.application.Adapters.Crypto;
import com.auth.application.Adapters.TokenAdapter;

import java.time.Instant;
import java.util.Objects;

public class DefaultGenerateTokenUseCase extends GenerateTokenUseCase{
    private UserGateway userGateway;
    private TokenAdapter tokenAdapter;
    private Crypto crypto;

    public DefaultGenerateTokenUseCase(final UserGateway userGateway, final Crypto crypto, final TokenAdapter tokenAdapter) {
        this.crypto = Objects.requireNonNull(crypto);
        this.tokenAdapter = Objects.requireNonNull(tokenAdapter);
        this.userGateway = Objects.requireNonNull(userGateway);
    }

    @Override
    public GenerateTokenOutput execute(GenerateTokenCommand aCommand) {
        final var anEmail  = Email.newEmail(aCommand.email());
        final var aPassword = aCommand.password();

        System.out.println(this.userGateway.findByEmail(anEmail));
        if(!this.userGateway.findByEmail(anEmail).isPresent()){
            throw DomainException.with(new Error("User not found"));
        }

        User userPersistence = this.userGateway.findByEmail(anEmail).orElseThrow(() -> DomainException.with(new Error("User not found")));

        if(!crypto.matches(aPassword, userPersistence.getPassword())) {
            throw DomainException.with(new Error("User or password dont correctly"));
        }

        Instant issueAt = Instant.now();
        Instant expiresAt = issueAt.plusSeconds(2700);
        String token = this.tokenAdapter.generateToken(userPersistence, issueAt, expiresAt);

        return GenerateTokenOutput.with(token, true);
    }
}
