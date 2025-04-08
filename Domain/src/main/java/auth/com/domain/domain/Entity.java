package auth.com.domain.domain;

import auth.com.domain.domain.validation.ValidationHandler;

import java.util.Objects;

public abstract class Entity<ID extends Identifier> {
    private final ID id;
    protected Entity(final ID id) {
        this.id = Objects.requireNonNull(id);
    }

    public ID getId() { return id; }

    protected abstract void validate(ValidationHandler aHandler);

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Entity<?> entity = (Entity<?>) o;
        return Objects.equals(getId(), entity.getId());
    }

    /**
     * Returns the hash code for the entity.
     * The hash code is based on the unique identifier.
     *
     * @return The hash code for the entity.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
