package auth.com.domain.domain.user;

import auth.com.domain.domain.validation.Error;
import auth.com.domain.domain.validation.ValidationHandler;
import auth.com.domain.domain.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator extends Validator {
    private final User user;
    protected UserValidator(ValidationHandler aHandler, User anUser) {
        super(aHandler);
        this.user = anUser;
    }
    @Override
    public void validate() {

    }

    private void checkEmail() {
        final Pattern EMAIL_PATTERN = Pattern.compile(
                "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
        );

        final var email = this.user.getEmail();
        if (email.isBlank()) {
            this.validationHandler().append(new Error("Email must not be null"));
            return;
        }

        if (email.isEmpty()) {
            this.validationHandler().append(new Error("Email must not be null"));
            return;
        }

        Matcher matcher = EMAIL_PATTERN.matcher(email);
        if (!matcher.matches()) {
            this.validationHandler().append(new Error("Email Invalid"));
        }

    }
}
