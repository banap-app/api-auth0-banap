package auth.com.domain.domain.applications;

import auth.com.domain.domain.Identifier;
import auth.com.domain.domain.user.UserID;

import java.util.Objects;
import java.util.UUID;

public class ApplicationsID extends Identifier {
    private final String value;

    private ApplicationsID(final String value) {
        this.value = Objects.requireNonNull(value);
    }
    public static ApplicationsID from(final String anId) {
        return new ApplicationsID(anId);
    }
    public static ApplicationsID unique() {
        return ApplicationsID.from(UUID.randomUUID().toString().toLowerCase());
    }


    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationsID that = (ApplicationsID) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
