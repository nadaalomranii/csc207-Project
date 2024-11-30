package interface_adapter.delete_assignment;

import entity.Course;
import use_case.delete_assignment.DeleteAssignmentInputBoundary;
import use_case.delete_assignment.DeleteAssignmentInputData;

/**
 * The Controller for the delete assignment use case.
 */
public class DeleteAssignmentController {
    private final DeleteAssignmentInputBoundary deleteAssignmentInteractor;

    public DeleteAssignmentController(DeleteAssignmentInputBoundary deleteAssignmentInteractor) {
        this.deleteAssignmentInteractor = deleteAssignmentInteractor;
    }

    /***
     * Executes the Delete Assignment Use Case.
     * @param assignmentName
     * @param course
     */
    public void execute(String assignmentName, Course course) {
        final DeleteAssignmentInputData deleteAssignmentInputData = new DeleteAssignmentInputData(assignmentName, course);
        deleteAssignmentInteractor.execute(deleteAssignmentInputData);
    }

    //TODO: implement or remove
    /***
     * Executes the "swtich to AssignmentView" Use case
     */
    public void switchToAssignmentView() { deleteAssignmentInteractor.switchToAssignmentView(); }
}

//TODO: where is this stuff used?