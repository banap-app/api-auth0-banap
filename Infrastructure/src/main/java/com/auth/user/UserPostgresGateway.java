package com.auth.user;

import auth.com.domain.domain.user.Email;
import auth.com.domain.domain.user.User;
import auth.com.domain.domain.user.UserGateway;
import com.auth.user.persistence.UserJpaEntity;
import com.auth.user.persistence.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPostgresGateway implements UserGateway {
    private final UserRepository userRepository;

    public UserPostgresGateway(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public Optional<User> findByEmail(Email email) {
        return this.userRepository.findByEmail(email.getValue()).map(UserJpaEntity::toDomain);
    }
}
