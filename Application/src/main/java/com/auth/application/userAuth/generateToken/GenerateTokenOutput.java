package com.auth.application.userAuth.generateToken;

public record GenerateTokenOutput(String token, Boolean success) {
    public static GenerateTokenOutput with(final String token, final Boolean success) {
        return new GenerateTokenOutput(token, success);
    }
}
