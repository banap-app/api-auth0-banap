package com.auth.user.models;

import com.auth.swagger.generate_token.GenerateTokenApiInputInterface;
import com.fasterxml.jackson.annotation.JsonProperty;

public record generateTokenApiInput(
        @JsonProperty("email") String email,
        @JsonProperty("password") String password,
        @JsonProperty("typeUser") Integer typeUser
)implements GenerateTokenApiInputInterface {
}
