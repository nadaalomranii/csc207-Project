package use_case.signup;

import entity.User;

/**
 * Output Data for the Signup use case.
 */
public class SignupOutputData {

    private final User user;

    private final boolean useCaseFailed;

    public SignupOutputData(User user, boolean useCaseFailed) {
        this.user = user;
        this.useCaseFailed = useCaseFailed;
    }

    public User getUser() { return user; }

    public boolean isUseCaseFailed() { return useCaseFailed; }
}
