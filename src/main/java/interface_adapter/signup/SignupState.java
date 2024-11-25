package interface_adapter.signup;

/**
 * The state for the Signup ViewModel.
 */
public class SignupState {
    private String username = "";
    private String usernameError;
    private String name = "";
    private String nameError;
    private String email = "";
    private String emailError;
    private String password = "";
    private String passwordError;
    private String confirmPassword = "";
    private String confirmPasswordError;

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getUsernameError() {return usernameError;}
    public void setUsernameError(String usernameError) {this.usernameError = usernameError;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getNameError() {return nameError;}
    public void setNameError(String nameError) {this.nameError = nameError;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getEmailError() {return emailError;}
    public void setEmailError(String emailError) {this.emailError = emailError;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public String getPasswordError() {return passwordError;}
    public void setPasswordError(String passwordError) {this.passwordError = passwordError;}

    public String getConfirmPassword() {return confirmPassword;}
    public void setConfirmPassword(String confirmPassword) {this.confirmPassword = confirmPassword;}
    public String getConfirmPasswordError() {return confirmPasswordError;}
    public void setConfirmPasswordError(String confirmPasswordError) {this.confirmPasswordError = confirmPasswordError;}

    @Override
    public String toString() {
        return "SignupState{"
                + "username='" + username + '\''
                + ", name='" + name + '\''
                + ", email='" + email + '\''
                + ", password='" + password + '\''
                + ", confirmPassword='" + confirmPassword + '\''
                + '}';
    }
}
