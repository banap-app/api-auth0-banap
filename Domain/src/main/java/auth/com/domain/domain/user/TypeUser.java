package auth.com.domain.domain.user;

import auth.com.domain.domain.exceptions.DomainException;
import auth.com.domain.domain.validation.Error;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TypeUser {

    private static final Map<Integer, String> validTypes = new HashMap<>();

    static {
        validTypes.put(1, "Engineer");
        validTypes.put(2, "Productor");
    }

    private final int typeId;
    private final String typeName;

    /**
     * Construtor para criar uma instância de TypeUser.
     * @param typeId O ID numérico do tipo de usuário.
     * @throws DomainException Lança uma exceção se o tipo for inválido.
     */
    public TypeUser(int typeId) {
        if (!validTypes.containsKey(typeId)) {
            throw DomainException.with(new Error("Invalid user type. Valid IDs are: " + validTypes.keySet()));
        }
        this.typeId = typeId;
        this.typeName = validTypes.get(typeId);
    }

    /**
     * Retorna o ID do tipo de usuário.
     * @return O ID do tipo de usuário.
     */
    public int getId() {
        return typeId;
    }

    /**
     * Retorna o nome do tipo de usuário.
     * @return O nome do tipo de usuário.
     */
    public String getName() {
        return typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeUser typeUser = (TypeUser) o;
        return typeId == typeUser.typeId && Objects.equals(typeName, typeUser.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, typeName);
    }

    @Override
    public String toString() {
        return "TypeUser{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}