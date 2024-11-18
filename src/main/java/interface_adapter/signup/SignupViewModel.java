package interface_adapter.signup;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the user Signup View.
 */
public class SignupViewModel extends ViewModel<SignupState> {
    public static final String TITLE_LABEL = "Signup View";
    public static final String NAME_LABEL = "Enter name";
    public static final String USERNAME_LABEL = "Enter username";
    public static final String EMAIL_LABEL = "Enter email";
    public static final String PASSWORD_LABEL = "Enter password";
    public static final String CONFIRM_LABEL = "Confirm password";

    public static final String SIGNUP_BUTTON_LABEL = "Sign up";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    public static final String TO_LOGIN_BUTTON_LABEL = "Go to login";

    public SignupViewModel() {
        super("sign up");
        setState(new SignupState());
    }
}
