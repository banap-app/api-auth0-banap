package auth.com.domain.domain.validation;

import java.util.List;

public interface ValidationHandler {

    ValidationHandler append(Error error);

    ValidationHandler append(ValidationHandler aHandler);

    <T> T validate(Validation<T> aValidation);

    List<Error> getErrors();

    default boolean hasErrors() {
        return getErrors() != null && !getErrors().isEmpty();
    }

    default Error firstError() {
        if (hasErrors()) {
            return getErrors().getFirst();
        } else {
            return null;
        }
    }

    interface Validation<T> {
        T validate();
    }
}
