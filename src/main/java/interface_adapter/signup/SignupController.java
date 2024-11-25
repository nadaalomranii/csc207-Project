package interface_adapter.signup;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

/**
 * The controller for the signup use case.
 */
public class SignupController {

    private final SignupInputBoundary userSignupUseCaseInteractor;

    public SignupController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    /**
     * Executes the signup use case.
     * @param username the username to sign up
     * @param name the user's name
     * @param email the user's email
     * @param password the password to sign up
     * @param confirmPassword the password repeated
     */
    public void execute(String username, String name, String email, String password, String confirmPassword) {
        final SignupInputData signupInputData = new SignupInputData(username, name, email, password, confirmPassword);
        userSignupUseCaseInteractor.execute(signupInputData);
    }

    /**
     * Executes the "switch to Course List View" use case.
     */
    public void switchToCourseListView() {
        userSignupUseCaseInteractor.switchToCourseListView();
    }
}
