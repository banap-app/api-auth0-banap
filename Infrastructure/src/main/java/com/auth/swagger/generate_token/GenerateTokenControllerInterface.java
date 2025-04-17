package com.auth.swagger.generate_token;

import com.auth.user.models.generateTokenApiInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Generate Token")
public interface GenerateTokenControllerInterface {

    @Operation(
            summary = "Route for login in app and generate token",
            description = "Route for login in app and generate token"
    )
    public ResponseEntity<?> generateToken(@RequestBody generateTokenApiInput generateTokenApiInput);
}
