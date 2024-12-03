package use_case.signup;

/**
 * The output boundary for the signup use case.
 */
public interface SignupOutputBoundary {

    /**
     * Prepares the success view for the signup use case.
     * @param outputdata the output data
     */
    void prepareSuccessView(SignupOutputData outputdata);

    /**
     * Prepare the fail view for the signup use case.
     * @param errorMessage the reason for the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Course List View.
     */
    void switchToCourseListView();

    /**
     * Switches to the Course List View.
     */
    void switchToLoginView();
}
