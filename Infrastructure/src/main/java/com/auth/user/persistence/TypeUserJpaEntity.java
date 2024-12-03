package com.auth.user.persistence;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity(name="TypeUser")
@Table(name="typeusers")
public class TypeUserJpaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "type_name", nullable = false, unique = true)
    private String typeName;

    // Construtor padrão (necessário para o JPA)
    public TypeUserJpaEntity() {
    }

    // Construtor para inicialização
    public TypeUserJpaEntity(String typeName) {
        this.typeName = typeName;
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "TypeUserJpaEntity{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}