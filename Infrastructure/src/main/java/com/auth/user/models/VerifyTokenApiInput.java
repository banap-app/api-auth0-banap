package com.auth.user.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record VerifyTokenApiInput(
        @JsonProperty("token") String token
) {
}
