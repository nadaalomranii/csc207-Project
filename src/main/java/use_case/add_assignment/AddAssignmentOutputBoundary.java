package use_case.add_assignment;

/**
 * The output boundary for the Add Assignment Use Case.
 */
public interface AddAssignmentOutputBoundary {
    void prepareSuccessView(EditAssignmentOutputData outputData);

    void prepareFailView(String errorMessage);
}
