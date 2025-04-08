package com.auth.application.userAuth.generateToken;

public record GenerateTokenCommand(
        String email,
        String password,
        Integer typeId
) {

    public static GenerateTokenCommand with(final String email, final String password, Integer typeId) {
        return new GenerateTokenCommand(email, password, typeId);
    }
}
