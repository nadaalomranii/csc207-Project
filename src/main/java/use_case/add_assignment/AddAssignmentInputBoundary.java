package use_case.add_assignment;

/**
 * The Add Assignment Use Case.
 */

public interface AddAssignmentInputBoundary {
    /**
     * Execute the Add Assignment Use Case.
     * @param addAssignmentInputData the input data for this use case
     */
    void execute(AddAssignmentInputData addAssignmentInputData);
}

