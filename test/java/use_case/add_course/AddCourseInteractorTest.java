package use_case.add_course;

import data_access.DataAccessInterface;
import entity.*;
import org.junit.jupiter.api.Test;
import use_case.add_course.AddCourseInputData;
import use_case.add_course.AddCourseOutputBoundary;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AddCourseInteractorTest {
    @Test
    /***
     * Test adding a course for the first time
     */
    void successTest() {
        CourseFactory courseFactory = new CommonCourseFactory();
        UserFactory userfactory = new CommonUserFactory();

        User user = userfactory.create("Miral", "miralyousef", "miral", "miral@gmail.com");
        AddCourseInputData courseInputData = new AddCourseInputData("Software Design", "CSC207", user);
        Course course = courseFactory.create(courseInputData.getName(), courseInputData.getCode(), new ArrayList<>());

        DataAccessInterface courseRepository = new DataAccessInterface();

        AddCourseOutputBoundary successPresenter = new AddCourseOutputBoundary() {
            @Override
            public void prepareSuccessView(AddCourseOutputData outputData) {
                assertEquals(outputData.getCode(), course.getCode());
                assertEquals(outputData.getName(), course.getName());
            }

            @Override
            public void prepareFailView(AddCourseOutputData addCourseOutputData, String errorMessage) {
                fail("Use case failure is unexpected/n"+errorMessage);
            }

            @Override
            public void switchToCourseView() {}
        };

        AddCourseInputBoundary interactor = new AddCourseInteractor(courseRepository, successPresenter, courseFactory);
        interactor.execute(courseInputData);
    }

    @Test
    /***
     * Test saving the same course twice.
     */
    void failTest() {
        CourseFactory courseFactory = new CommonCourseFactory();
        UserFactory userfactory = new CommonUserFactory();
        DataAccessInterface courseRepository = new DataAccessInterface();

        User user = userfactory.create("Miral", "miralyousef", "miral", "miral@gmail.com");
        AddCourseInputData courseInputData = new AddCourseInputData("Software Design", "CSC207", user);
        Course course = courseFactory.create(courseInputData.getName(), courseInputData.getCode(), new ArrayList<>());

        courseRepository.saveCourse(course, user);

        AddCourseOutputBoundary successPresenter = new AddCourseOutputBoundary() {

            @Override
            public void prepareSuccessView(AddCourseOutputData outputData) {
                fail("Use case success is unexpected");
            }

            @Override
            public void prepareFailView(AddCourseOutputData outputData, String errorMessage) {
                assertNull(outputData.getCourse());
                assertTrue(outputData.isUseCaseFailed());
            }

            @Override
            public void switchToCourseView() {

            }
        };

        AddCourseInputBoundary interactor = new AddCourseInteractor(courseRepository, successPresenter, courseFactory);
        interactor.execute(courseInputData);
    }
}
