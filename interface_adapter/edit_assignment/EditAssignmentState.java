package interface_adapter.edit_assignment;

/**
 * The State information representing the logged-in user.
 */
// TODO change according to make it specific to edit assignment
public class EditAssignmentState {
    private String username = "";

    private String password = "";
    private String passwordError;

    public EditAssignmentState(EditAssignmentState copy) {
        username = copy.username;
        password = copy.password;
        passwordError = copy.passwordError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public EditAssignmentState() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getPassword() {
        return password;
    }
}
