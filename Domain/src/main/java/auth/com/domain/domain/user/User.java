package auth.com.domain.domain.user;

import auth.com.domain.domain.AggregateRoot;
import auth.com.domain.domain.validation.ValidationHandler;

import java.util.Objects;

public class User extends AggregateRoot<UserID> {

    private final String email;
    private final String password;
    private final TypeUser typeUser;

    private User(UserID userID, String email, String password, TypeUser typeUser) {
        super(userID);
        this.email = email;
        this.password = password;
        this.typeUser = typeUser;
    }

    public static User newUser(final String email, final String password, final int typeId) {
        final var userId = UserID.unique();
        final var aTypeUser = new TypeUser(typeId);
        return new User(userId, email, password, aTypeUser);
    }

    public void validate(ValidationHandler handler) {
        new UserValidator(handler, this).validate();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public TypeUser getTypeUser() {
        return typeUser;
    }

    public static User with(final UserID userId, final String anEmail, final String aPassword, final int typeId) {
        return new User(userId, anEmail, aPassword, new TypeUser(typeId));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(typeUser, user.typeUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email, password, typeUser);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", typeUser='" + typeUser.getName() + '\'' +
                '}';
    }
}