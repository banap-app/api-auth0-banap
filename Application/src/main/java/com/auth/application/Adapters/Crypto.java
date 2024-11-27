package com.auth.application.Adapters;

public interface Crypto {
    String encode(String rawPassword);
    boolean matches(String rawPassword, String encodedPassword);
}
