package src.main.java.use_case.edit_assignment;

public interface EditAssignmentOutputBoundary {
    /**
     * Prepares the success view for the Edit Assignment Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(EditAssignmentOutputData outputData);

    /**
     * Prepares the failure view for the Edit Assignment Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}

