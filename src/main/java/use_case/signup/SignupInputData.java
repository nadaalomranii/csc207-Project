package use_case.signup;

/**
 * The Input Data for the Signup use case.
 */
public class SignupInputData {

    private final String username;
    private final String name;
    private final String email;
    private final String password;
    private final String confirmPassword;

    public SignupInputData(String username, String name, String email, String password, String confirmPassword) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    String getUsername() {return username;}

    String getName() {return name;}

    String getEmail() {return email;}

    String getPassword() {return password;}

    public String getConfirmPassword() {return confirmPassword;}
}
