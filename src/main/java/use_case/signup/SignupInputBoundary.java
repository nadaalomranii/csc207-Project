package use_case.signup;

/**
 * Input Boundary for actions related to signing up.
 */
public interface SignupInputBoundary {

    /**
     * Executes the signup use case.
     * @param signupInputData the input data
     */
    void execute(SignupInputData signupInputData);

    /**
     * Executes the switch to course list view use case.
     */
    void switchToCourseListView();
}
