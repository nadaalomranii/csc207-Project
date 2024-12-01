package interface_adapter.edit_assignment;

import entity.Assignment;
import use_case.edit_assignment.EditAssignmentInputBoundary;
import use_case.edit_assignment.EditAssignmentInputData;
import use_case.edit_assignment.EditAssignmentInteractor;


/**
 * Controller for the Edit Assignment Use Case.
 */
public class EditAssignmentController {
    final EditAssignmentInputBoundary userEditAssignmentUseCaseInteractor;

    public EditAssignmentController(EditAssignmentInputBoundary userEditAssignmentUseCaseInteractor) {
        this.userEditAssignmentUseCaseInteractor = userEditAssignmentUseCaseInteractor;
    }

    /**
     * Executes the edit assignment Use Case.
     * @param newScore the new score
     * @param assignment the assignment whose score has to change
     */
    public void execute(float newScore, Assignment assignment) {
        final EditAssignmentInputData editAssignmentInputData = new EditAssignmentInputData(assignment, newScore);

        EditAssignmentInteractor.execute(editAssignmentInputData);
    }
}