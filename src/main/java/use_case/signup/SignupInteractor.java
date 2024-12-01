package use_case.signup;

import entity.User;
import entity.UserFactory;

import java.util.ArrayList;

/**
 * The signup interactor.
 */
public class SignupInteractor implements SignupInputBoundary {
    private final SignupUserDataAccessInterface userDataAccessObject;
    private final SignupOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface userDataAccessObject,
                            SignupOutputBoundary userPresenter,
                            UserFactory userFactory) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        }
        else if (!signupInputData.getPassword().equals(signupInputData.getConfirmPassword())) {
            userPresenter.prepareFailView("Passwords do not match.");
        }
        else {
            final User user = userFactory.create(signupInputData.getName(),
                    signupInputData.getUsername(),
                    signupInputData.getPassword(),
                    signupInputData.getEmail());
            userDataAccessObject.save(user, new ArrayList<>());

            final SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }

    @Override
    public void switchToCourseListView() {
        userPresenter.switchToCourseListView();
    }
}
