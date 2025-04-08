package com.auth.application.userAuth.generateToken;

import auth.com.domain.domain.exceptions.DomainException;
import auth.com.domain.domain.user.User;
import auth.com.domain.domain.user.UserGateway;
import auth.com.domain.domain.validation.Error;
import auth.com.domain.domain.validation.handler.Notification;
import com.auth.application.Adapters.Crypto;
import com.auth.application.Adapters.TokenAdapter;
import com.auth.application.Result;

import java.time.Instant;
import java.util.Objects;

public class DefaultGenerateTokenUseCase extends GenerateTokenUseCase{
    private UserGateway userGateway;
    private TokenAdapter tokenAdapter;
    private Crypto crypto;

    public DefaultGenerateTokenUseCase(final UserGateway userGateway, final Crypto crypto, final TokenAdapter tokenAdapter) {
        this.crypto = Objects.requireNonNull(crypto);
        this.tokenAdapter = Objects.requireNonNull(tokenAdapter);
        this.userGateway = Objects.requireNonNull(userGateway);
    }

    @Override
    public Result<GenerateTokenOutput,Notification> execute(GenerateTokenCommand aCommand) {
        final var anEmail  = aCommand.email();
        final var aPassword = aCommand.password();
        final var aType = aCommand.typeId();

        final var anUser = User.newUser(anEmail, aPassword, aType);
        final Notification notification = Notification.create();
       try {
           anUser.validate(notification);
            if(notification.hasErrors()){
                return new Result<>(null,notification);
            }

           if(!this.userGateway.findByEmail(anEmail).isPresent()){
               notification.append(new Error("User not found"));
               return new Result<>(null, notification);
           }

           User userPersistence = this.userGateway.findByEmail(anEmail).orElseThrow(() -> DomainException.with(new Error("User not found")));

           if(!crypto.matches(aPassword, userPersistence.getPassword())) {
               notification.append(new Error("User or password dont correctly"));
               return new Result<>(null, notification);
           }

           if(anUser.getTypeUser().getId() != userPersistence.getTypeUser().getId()) {
               notification.append(new Error("User type dont correctly"));
               return new Result<>(null, notification);
           }

           Instant issueAt = Instant.now();

           Instant expiresAt = issueAt.plusSeconds(2700);

           String token = this.tokenAdapter.generateToken(userPersistence, issueAt, expiresAt);

           return new Result<>(GenerateTokenOutput.with(token, true), null);

       } catch(DomainException ex) {
           notification.append(new Error(ex.getMessage()));
           return new Result<>(null, notification);
       }
    }
}
