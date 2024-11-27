package com.auth.application.Adapters;

import auth.com.domain.domain.user.User;

import java.time.Instant;

public interface TokenAdapter {

    String generateToken(User userAuth, Instant issuedAt, Instant expiresAt);


    String getSubject(String token);
}