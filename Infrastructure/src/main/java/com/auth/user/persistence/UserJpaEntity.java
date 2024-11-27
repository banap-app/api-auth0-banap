package com.auth.user.persistence;

import auth.com.domain.domain.user.User;
import auth.com.domain.domain.user.UserID;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity(name="User")
@Table(name="Users")
public class UserJpaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    // Construtor padrão (necessário para o JPA)
    public UserJpaEntity() {
    }

    // Construtor para inicialização
    public UserJpaEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User toDomain() {
        return User.with(UserID.from(getId().toString()), getEmail(), getPassword());
    }

    // Getters e Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserJpaEntity{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
