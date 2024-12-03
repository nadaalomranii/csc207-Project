package use_case.change_password;

import data_access.DataAccessInterface;
import entity.CommonUserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChangePasswordInteractorTest {

    @Test
    void successTest() {
        ChangePasswordInputData inputData = new ChangePasswordInputData("sarahhira",
                "Sarah",
                "test@gmail.com",
                "password");
        ChangePasswordUserDataAccessInterface userRepository = new DataAccessInterface();

        // This creates a successPresenter that tests whether the test case is as we expect.
        ChangePasswordOutputBoundary successPresenter = new ChangePasswordOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangePasswordOutputData user) {
                // Check that the output data is correct, and that the use case was successful.
                assertEquals("sarahhira", user.getUsername());
                assertFalse(user.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(userRepository, successPresenter, new CommonUserFactory());
        interactor.execute(inputData);
    }
}