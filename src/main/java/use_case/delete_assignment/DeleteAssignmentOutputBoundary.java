package use_case.delete_assignment;

/**
 * The output boundary for the delete assignment use case.
 */
public interface DeleteAssignmentOutputBoundary {
    /**
     * Prepares the Success view for the delete assignment use case.
     * @param deleteAssignmentOutputData: the output data
     */
    void prepareSuccessView(DeleteAssignmentOutputData deleteAssignmentOutputData);

    /**
     * Prepares the fail view for the delete assignment use case.
     * @param deleteAssignmentOutputData: the output data
     */
    void prepareFailView(DeleteAssignmentOutputData deleteAssignmentOutputData);

    /**
     * Switches to the assignment list view.
     */
    void switchToAssignmentView();
}
