package com.auth.api.controllers;

import auth.com.domain.domain.exceptions.DomainException;
import com.auth.application.userAuth.verifyToken.ValidateTokenCommand;
import com.auth.application.userAuth.verifyToken.ValidateTokenOutput;
import com.auth.application.userAuth.verifyToken.ValidateTokenUseCase;
import com.auth.user.models.VerifyTokenApiInput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class VerifyTokenController {
    private final ValidateTokenUseCase validateTokenUseCase;

    public VerifyTokenController(final ValidateTokenUseCase validateTokenUseCase) {
        this.validateTokenUseCase = Objects.requireNonNull(validateTokenUseCase);
    }

    @PostMapping(value = "/api/auth/verify")
    public ResponseEntity<?> verifyToken(@RequestBody VerifyTokenApiInput input) {
        final String token = input.token();

        final var command = new ValidateTokenCommand(token);

        try {
            final ValidateTokenOutput output = validateTokenUseCase.execute(command);

            return ResponseEntity.ok(output);
        } catch (DomainException e) {
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("code", HttpStatus.UNPROCESSABLE_ENTITY.value());
            responseBody.put("errors", e.getErrors());
            responseBody.put("status", HttpStatus.UNPROCESSABLE_ENTITY.name());
            return ResponseEntity.unprocessableEntity().body(responseBody);
        }
    }
}
