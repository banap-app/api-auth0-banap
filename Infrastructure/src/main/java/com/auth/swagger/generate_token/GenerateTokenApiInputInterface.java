package com.auth.swagger.generate_token;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Modelo para geração de token")
public interface GenerateTokenApiInputInterface {
    @Schema(description = "Email do usuário", required = true)
    @JsonProperty("email")
    String email();

    @Schema(description = "Senha do usuário", required = true)
    @JsonProperty("password")
    String password();

    @Schema(description = "Tipo de usuário", required = true)
    @JsonProperty("typeUser")
    Integer typeUser();
}