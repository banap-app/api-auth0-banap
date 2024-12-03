package com.auth.user.persistence;

import auth.com.domain.domain.user.User;
import auth.com.domain.domain.user.UserID;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity(name="User")
@Table(name="users")
public class UserJpaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password_user;

    @ManyToOne
    @JoinColumn(name = "type_user_id", nullable = false)
    private TypeUserJpaEntity typeUser;

    // Construtor padrão (necessário para o JPA)
    public UserJpaEntity() {
    }

    // Construtor para inicialização
    public UserJpaEntity(String email, String password_user, TypeUserJpaEntity typeUser) {
        this.email = email;
        this.password_user = password_user;
        this.typeUser = typeUser;
    }

    public User toDomain() {
        return User.with(UserID.from(getId().toString()), getEmail(), getPassword(), typeUser.getId());
    }

    // Getters e Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password_user;
    }

    public void setPassword(String password_user) {
        this.password_user = password_user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public TypeUserJpaEntity getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(TypeUserJpaEntity typeUser) {
        this.typeUser = typeUser;
    }

    @Override
    public String toString() {
        return "UserJpaEntity{" +
                "email='" + email + '\'' +
                ", password='" + password_user + '\'' +
                ", typeUser='" + typeUser.getTypeName() + '\'' +
                '}';
    }
}