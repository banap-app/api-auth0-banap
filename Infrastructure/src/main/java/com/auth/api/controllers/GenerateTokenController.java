package com.auth.api.controllers;

import auth.com.domain.domain.exceptions.DomainException;
import com.auth.application.userAuth.generateToken.GenerateTokenCommand;
import com.auth.application.userAuth.generateToken.GenerateTokenOutput;
import com.auth.application.userAuth.generateToken.GenerateTokenUseCase;
import com.auth.user.models.generateTokenApiInput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class GenerateTokenController {
    private GenerateTokenUseCase generateTokenUseCase;

    public GenerateTokenController(final GenerateTokenUseCase generateTokenUseCase) {
        this.generateTokenUseCase = Objects.requireNonNull(generateTokenUseCase);
    }

    @PostMapping(value = "/api/auth")
    public ResponseEntity<?> generateToken(@RequestBody generateTokenApiInput input) {
        final var anEmail = input.email();
        final var aPassword = input.password();
        System.out.println("Password and Email " + aPassword + "  " + anEmail);

        final var aCommand = GenerateTokenCommand.with(anEmail, aPassword);
        try {
            final GenerateTokenOutput output = this.generateTokenUseCase.execute(aCommand);
            return ResponseEntity.ok(output);
        } catch (DomainException e) {
            System.out.println(e.getErrors());
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("code", HttpStatus.UNPROCESSABLE_ENTITY.value());
            responseBody.put("errors", e.getErrors());
            responseBody.put("status", HttpStatus.UNPROCESSABLE_ENTITY.name());
            return ResponseEntity.unprocessableEntity().body(responseBody);
        }
    }
}


