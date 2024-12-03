package com.auth.application.userAuth.verifyToken;

public record ValidateTokenCommand(
        String token
) {
    public static ValidateTokenCommand with(String token) {
        return new ValidateTokenCommand(token);
    }
}
