package use_case.signup;

import data_access.DataAccessInterface;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.course_list.CourseListViewModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SignupInteractorTest {

    @Test
    void successTest() {
        SignupInputData inputData = new SignupInputData("sarahhira",
                "Sarah",
                "test@gmail.com",
                "password",
                "password");
        SignupUserDataAccessInterface userRepository = new DataAccessInterface();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CourseListViewModel courseListViewModel = new CourseListViewModel();

        // This creates a successPresenter that tests whether the test case is as we expect.
        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertEquals("sarahhira", user.getUser().getUsername());
                assertTrue(userRepository.userExistsByName("sarahhira"));
                assertFalse(user.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToCourseListView() {
                viewManagerModel.setState(courseListViewModel.getViewName());
                viewManagerModel.firePropertyChanged();
                assertEquals("Course List", viewManagerModel.getState());
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, successPresenter, new CommonUserFactory());
        interactor.execute(inputData);
        interactor.switchToCourseListView();
    }

    @Test
    void failurePasswordMismatchTest() {
        SignupInputData inputData = new SignupInputData("sarahhira",
                "Sarah",
                "test@gmail.com",
                "password",
                "wrong");
        SignupUserDataAccessInterface userRepository = new DataAccessInterface();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CourseListViewModel courseListViewModel = new CourseListViewModel();

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords do not match.", error);
            }

            @Override
            public void switchToCourseListView() {
                viewManagerModel.setState(courseListViewModel.getViewName());
                viewManagerModel.firePropertyChanged();
                assertEquals("Course List", viewManagerModel.getState());
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);
        interactor.switchToCourseListView();
    }

    @Test
    void failureUserExistsTest() {
        SignupInputData inputData = new SignupInputData("sarahhira",
                "Sarah",
                "test@gmail.com",
                "test",
                "wrong");
        SignupUserDataAccessInterface userRepository = new DataAccessInterface();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CourseListViewModel courseListViewModel = new CourseListViewModel();

        // Add Sarah to the repo so that when we check later they already exist
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Sarah", "sarahhira", "password", "test@gmail.com");
        userRepository.save(user, new ArrayList<>());

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User already exists.", error);
            }


            @Override
            public void switchToCourseListView() {
                viewManagerModel.setState(courseListViewModel.getViewName());
                viewManagerModel.firePropertyChanged();
                assertEquals("Course List", viewManagerModel.getState());
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);
        interactor.switchToCourseListView();
    }
}