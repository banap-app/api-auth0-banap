package com.auth.application.userAuth.verifyToken;

public record ValidateTokenOutput(String decodedToken, Boolean success) {
    public static ValidateTokenOutput with(final String decodedToken, final Boolean success) {
        return new ValidateTokenOutput(decodedToken, success);
    }
}
