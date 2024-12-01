package use_case.change_password;

/**
 * The input data for the Change Password Use Case.
 */
public class ChangePasswordInputData {

    private final String username;
    private final String name;
    private final String email;
    private final String password;

    public ChangePasswordInputData(String username, String name, String email, String password) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    String getUsername() {
        return username;
    }

    String getName() {return name;}

    String getEmail() {return email;}

    String getPassword() {
        return password;
    }

}
