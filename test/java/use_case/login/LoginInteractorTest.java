package use_case.login;

import data_access.DataAccessInterface;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.signup.SignupViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class LoginInteractorTest {

    @Test
    void successTest() {
        LoginInputData inputData = new LoginInputData("sarahhira", "password");
        LoginUserDataAccessInterface userRepository = new DataAccessInterface();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupViewModel signupViewModel = new SignupViewModel();

        // For the success test, we need to add Paul to the data access repository before we log in.
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Sarah", "sarahhira", "password", "test@gmail.com");
        userRepository.save(user);

        // This creates a successPresenter that tests whether the test case is as we expect.
        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals("sarahhira", user.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToSignUpView() {
                viewManagerModel.setState(signupViewModel.getViewName());
                viewManagerModel.firePropertyChanged();
                assertEquals("sign up", viewManagerModel.getState());
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
        interactor.switchToSignUpView();
    }

    @Test
    void successUserLoggedInTest() {
        LoginInputData inputData = new LoginInputData("sarahhira", "password");
        LoginUserDataAccessInterface userRepository = new DataAccessInterface();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupViewModel signupViewModel = new SignupViewModel();

        // For the success test, we need to add Sarah to the data access repository before we log in.
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Sarah", "sarahhira", "password", "test@gmail.com");
        userRepository.save(user);

        // This creates a successPresenter that tests whether the test case is as we expect.
        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals("sarahhira", userRepository.getCurrentUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToSignUpView() {
                viewManagerModel.setState(signupViewModel.getViewName());
                viewManagerModel.firePropertyChanged();
                assertEquals("sign up", viewManagerModel.getState());
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, successPresenter);
        assertEquals(null, userRepository.getCurrentUsername());

        interactor.execute(inputData);
        interactor.switchToSignUpView();
    }

    @Test
    void failurePasswordMismatchTest() {
        LoginInputData inputData = new LoginInputData("sarahhira", "wrong");
        LoginUserDataAccessInterface userRepository = new DataAccessInterface();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupViewModel signupViewModel = new SignupViewModel();

        // For this failure test, we need to add Paul to the data access repository before we log in, and
        // the passwords should not match.
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Sarah", "sarahhira", "password", "test@gmail.com");
        userRepository.save(user);

        // This creates a presenter that tests whether the test case is as we expect.
        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Incorrect password for \"sarahhira\".", error);
            }

            @Override
            public void switchToSignUpView() {
                viewManagerModel.setState(signupViewModel.getViewName());
                viewManagerModel.firePropertyChanged();
                assertEquals("sign up", viewManagerModel.getState());
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
        interactor.switchToSignUpView();
    }

    @Test
    void failureUserDoesNotExistTest() {
        LoginInputData inputData = new LoginInputData("sarahhira", "password");
        LoginUserDataAccessInterface userRepository = new DataAccessInterface();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupViewModel signupViewModel = new SignupViewModel();

        // Add Paul to the repo so that when we check later they already exist

        // This creates a presenter that tests whether the test case is as we expect.
        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("sarahhira: Account does not exist.", error);
            }

            @Override
            public void switchToSignUpView() {
                viewManagerModel.setState(signupViewModel.getViewName());
                viewManagerModel.firePropertyChanged();
                assertEquals("sign up", viewManagerModel.getState());
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
        interactor.switchToSignUpView();
    }
}