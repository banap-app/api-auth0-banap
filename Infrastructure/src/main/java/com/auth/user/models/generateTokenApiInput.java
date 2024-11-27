package com.auth.user.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record generateTokenApiInput(
        @JsonProperty("email") String email,
        @JsonProperty("password") String password
) {
}
