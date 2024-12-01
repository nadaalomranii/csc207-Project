package interface_adapter.edit_assignment;

import entity.Assignment;
import use_case.edit_assignment.EditAssignmentInputBoundary;
import use_case.edit_assignment.EditAssignmentInputData;
import use_case.edit_assignment.EditAssignmentInteractor;

import java.util.Date;


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
     * @param name the new name
     * @param newScore the new score
     * @param weight the new weight
     * @param dueDate the new duedate
     */
    public void execute(String name, float newScore, float weight, Date dueDate) {
        final EditAssignmentInputData editAssignmentInputData = new EditAssignmentInputData(name, newScore, weight, dueDate);

        EditAssignmentInteractor.execute(editAssignmentInputData);
    }
}