package com.auth.application.userAuth.generateToken;

public record GenerateTokenCommand(
        String email,
        String password
) {

    public static GenerateTokenCommand with(final String email, final String password) {
        return new GenerateTokenCommand(email, password);
    }
}
