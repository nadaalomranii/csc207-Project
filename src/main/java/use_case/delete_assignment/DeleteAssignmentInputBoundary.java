package use_case.delete_assignment;

/**
 * The input boundary for the Delete Course Use Case.
 */
public interface DeleteAssignmentInputBoundary {
    /**
     * Executes the Delete Assignment Use Case.
     * @param deleteAssignmentInputData: the input data
     */
    void execute(DeleteAssignmentInputData deleteAssignmentInputData);

    /**
     * Executes the switch to course list view.
     */
    void switchToAssignmentView();

}
//DONE