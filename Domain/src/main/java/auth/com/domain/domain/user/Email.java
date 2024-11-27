package auth.com.domain.domain.user;

import auth.com.domain.domain.validation.Error;
import auth.com.domain.domain.validation.ValidationHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {
    private final String email;

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    );

    private Email(String email) {
        this.email = email;
    }

    public void validate(ValidationHandler handler) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        if (!matcher.matches()) {
            handler.append(new Error("Email Invalid"));
        }
    }

    public static Email newEmail(String email) {
        return new Email(email);
    }

    public String getValue() {
        return email;
    }

}
