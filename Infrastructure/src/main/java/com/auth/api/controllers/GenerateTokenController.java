package com.auth.api.controllers;

import auth.com.domain.domain.exceptions.DomainException;
import auth.com.domain.domain.validation.handler.Notification;
import com.auth.application.Result;
import com.auth.application.userAuth.generateToken.DefaultGenerateTokenUseCase;
import com.auth.application.userAuth.generateToken.GenerateTokenCommand;
import com.auth.application.userAuth.generateToken.GenerateTokenOutput;
import com.auth.application.userAuth.generateToken.GenerateTokenUseCase;
import com.auth.user.models.generateTokenApiInput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(
 "/api/auth/user"
)
public class GenerateTokenController {
    private DefaultGenerateTokenUseCase generateTokenUseCase;

    public GenerateTokenController(final DefaultGenerateTokenUseCase generateTokenUseCase) {
        this.generateTokenUseCase = Objects.requireNonNull(generateTokenUseCase);
    }


    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody generateTokenApiInput input) {
        try{
            final var anEmail = input.email();
            final var aPassword = input.password();
            final var aType = input.typeUser();

            final var aCommand = GenerateTokenCommand.with(anEmail, aPassword, aType);
            final Result<GenerateTokenOutput, Notification> output = this.generateTokenUseCase.execute(aCommand);
            if (output.getFirstValue() != null) {
                return ResponseEntity.ok(output.getFirstValue());
            }else {
                Map<String, Object> responseBody = new HashMap<>();
                responseBody.put("code", HttpStatus.UNPROCESSABLE_ENTITY.value());
                responseBody.put("errors", output.getSecondValue());
                responseBody.put("status", HttpStatus.UNPROCESSABLE_ENTITY.name());
                return ResponseEntity.unprocessableEntity().body(output.getSecondValue());
            }
        }catch(Exception e) {
            return ResponseEntity.status(500).body(new Result<>(null, Notification.create(new Error("Erro interno do servidor: " + e.getMessage()))));
        }
    }
}


