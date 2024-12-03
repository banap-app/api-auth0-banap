package com.auth.application.userAuth.verifyToken;

import auth.com.domain.domain.exceptions.DomainException;
import auth.com.domain.domain.validation.Error;
import com.auth.application.Adapters.TokenAdapter;

import java.util.Objects;

public class DefaultValidateTokenUseCase extends ValidateTokenUseCase {
    private final TokenAdapter tokenAdapter;

    public DefaultValidateTokenUseCase(final TokenAdapter tokenAdapter) {
        this.tokenAdapter = Objects.requireNonNull(tokenAdapter);
    }

    @Override
    public ValidateTokenOutput execute(ValidateTokenCommand command) {
        final String token = command.token();

        if (token == null || token.isBlank()) {
            throw DomainException.with(new Error("Token is missing"));
        }

        if (!tokenAdapter.verifyToken(token)) {
            throw DomainException.with(new Error("Invalid or expired token"));
        }

        final String decodedToken = tokenAdapter.getSubject(token);

        return ValidateTokenOutput.with(decodedToken, true);
    }
}
