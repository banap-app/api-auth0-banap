package auth.com.domain.domain.user;

import auth.com.domain.domain.AggregateRoot;
import auth.com.domain.domain.validation.ValidationHandler;

import java.util.Objects;

public class User extends AggregateRoot<UserID> {

    private final Email email;
    private final String password;

    private User(UserID userID, Email email, String password) {
        super(userID);
        this.email = email;
        this.password = password;
    }

    public static User newUser(final String email, final String password) {
        final var userId = UserID.unique();
        final var anEmail = Email.newEmail(email);
        return new User(userId, anEmail, password);
    }

    public void validate(ValidationHandler handler) {
        this.email.validate(handler);
    }

    public Email getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static User with(final UserID userId, final String anEmail, final String aPassword) {
        return new User(userId, Email.newEmail(anEmail), aPassword);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email, password);
    }
}
