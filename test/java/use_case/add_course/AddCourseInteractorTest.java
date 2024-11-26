package use_case.add_course;

import data_access.DataAccessInterface;
import entity.*;
import org.junit.jupiter.api.Test;
import use_case.add_course.AddCourseInputData;
import use_case.add_course.AddCourseOutputBoundary;

import static org.junit.jupiter.api.Assertions.*;

class AddCourseInteractorTest {
    @Test
    /***
     * Test adding a course for the first time
     */
    void successTest() {
        CourseFactory factory = new CommonCourseFactory();
        AddCourseInputData courseInputData = new AddCourseInputData("Software Design", "CSC207");
        Course course = factory.create(courseInputData.getName(), courseInputData.getCode());
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

        AddCourseInputBoundary interactor = new AddCourseInteractor(courseRepository, successPresenter, factory);
        interactor.execute(courseInputData);
    }

    @Test
    /***
     * Test saving the same course twice.
     */
    void failTest() {
        CourseFactory factory = new CommonCourseFactory();
        DataAccessInterface courseRepository = new DataAccessInterface();

        AddCourseInputData courseInputData = new AddCourseInputData("Software Design", "CSC207");

        Course course = factory.create(courseInputData.getName(), courseInputData.getCode());
        courseRepository.saveCourse(course);

        AddCourseOutputBoundary successPresenter = new AddCourseOutputBoundary() {

            @Override
            public void prepareSuccessView(AddCourseOutputData outputData) {
                fail("Use case success is unexpected/n");
            }

            @Override
            public void prepareFailView(AddCourseOutputData outputData, String errorMessage) {
                assertNull(outputData.getCourse());
                assertTrue(outputData.isUseCaseFailed());
            }

            @Override
            public void switchToCourseView() {}
        };

        AddCourseInputBoundary interactor = new AddCourseInteractor(courseRepository, successPresenter, factory);
        interactor.execute(courseInputData);
    }
}
