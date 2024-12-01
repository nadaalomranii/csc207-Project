package interface_adapter.edit_assignment;

import entity.Assignment;
import entity.User;
import src.main.java.use_case.edit_assignment.EditAssignmentInputBoundary;
import src.main.java.use_case.edit_assignment.EditAssignmentInputData;
import src.main.java.use_case.edit_assignment.EditAssignmentInteractor;

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
    public void execute(float newScore, Assignment assignment, User user) {
        final EditAssignmentInputData editAssignmentInputData = new EditAssignmentInputData();

        EditAssignmentInteractor.execute(editAssignmentInputData);
    }
}