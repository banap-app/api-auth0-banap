package auth.com.domain.domain.applications;

import auth.com.domain.domain.AggregateRoot;
import auth.com.domain.domain.validation.ValidationHandler;

public class Applications extends AggregateRoot<ApplicationsID> {

    private Applications(final ApplicationsID applicationsID) {
        super(applicationsID);
    }
    @Override
    protected void validate(ValidationHandler aHandler) {

    }
}
