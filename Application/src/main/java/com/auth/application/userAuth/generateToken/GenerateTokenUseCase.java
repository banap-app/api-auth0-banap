package com.auth.application.userAuth.generateToken;

import auth.com.domain.domain.validation.ValidationHandler;
import auth.com.domain.domain.validation.handler.Notification;
import com.auth.application.Result;
import com.auth.application.UseCase;

import java.util.List;

public abstract class GenerateTokenUseCase extends UseCase<GenerateTokenCommand, Result<GenerateTokenOutput, ? extends ValidationHandler>> {
}
