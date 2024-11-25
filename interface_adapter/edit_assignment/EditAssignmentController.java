package interface_adapter.edit_assignment;

import use_case.edit_assignment.EditAssignmentInputBoundary;
import use_case.edit_assignment.EditAssignmentInputData;

/**
 * Controller for the Edit Assignment Use Case.
 */
public class EditAssignmentController {
    private final EditAssignmentInputBoundary userEditAssignmentUseCaseInteractor;

    public EditAssignmentController(EditAssignmentInputBoundary userEditAssignmentUseCaseInteractor) {
        this.userEditAssignmentUseCaseInteractor = userEditAssignmentUseCaseInteractor;
    }

    /**
     * Executes the edit assignment Use Case.
     * @param n the new password
     * @param username the user whose password to change
     */
    public void execute(String password, String username) {
        final ChangePasswordInputData changePasswordInputData = new ChangePasswordInputData(username, password);

        userChangePasswordUseCaseInteractor.execute(changePasswordInputData);
    }
}