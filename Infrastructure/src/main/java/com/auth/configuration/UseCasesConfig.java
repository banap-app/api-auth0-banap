package com.auth.configuration;

import com.auth.application.Adapters.Crypto;
import com.auth.application.Adapters.TokenAdapter;
import com.auth.application.userAuth.generateToken.DefaultGenerateTokenUseCase;
import com.auth.application.userAuth.generateToken.GenerateTokenUseCase;
import com.auth.application.userAuth.verifyToken.DefaultValidateTokenUseCase;
import com.auth.application.userAuth.verifyToken.ValidateTokenUseCase;
import com.auth.user.UserPostgresGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {
    private final UserPostgresGateway userPostgresGateway;
    private final TokenAdapter tokenService;
    private final Crypto cryptoService;

    public UseCasesConfig(final UserPostgresGateway userPostgresGateway, final TokenAdapter tokenAdapter, final Crypto crypto) {
        this.cryptoService = crypto;
        this.userPostgresGateway = userPostgresGateway;
        this.tokenService = tokenAdapter;
    }

    @Bean
    public DefaultGenerateTokenUseCase generateTokenUseCase() {
        return new DefaultGenerateTokenUseCase(userPostgresGateway,cryptoService, tokenService);
    }

    @Bean
    public ValidateTokenUseCase validateTokenUseCase() {
        return new DefaultValidateTokenUseCase(tokenService);
    }
}
