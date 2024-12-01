package interface_adapter.change_password;

import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInputData;

/**
 * Controller for the Change Password Use Case.
 */
public class ChangePasswordController {
    private final ChangePasswordInputBoundary userChangePasswordUseCaseInteractor;

    public ChangePasswordController(ChangePasswordInputBoundary userChangePasswordUseCaseInteractor) {
        this.userChangePasswordUseCaseInteractor = userChangePasswordUseCaseInteractor;
    }

    /**
     * Executes the Change Password Use Case.
     * @param username the username identifying this user
     * @param name the user's name
     * @param email the user's contact email
     * @param password the user's password
     */
    public void execute(String username, String name, String email, String password) {
        final ChangePasswordInputData changePasswordInputData = new ChangePasswordInputData(username, name, email, password);

        userChangePasswordUseCaseInteractor.execute(changePasswordInputData);
    }
}
