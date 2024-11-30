package use_case.delete_course;

import data_access.DataAccessInterface;
import entity.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteCourseInteractorTest {
    @Test
    void successTest() {
        CourseFactory courseFactory = new CommonCourseFactory();

        // This is the course we want to save then delete
        Course course = courseFactory.create("Software Design", "CSC207");

        DeleteCourseInputData inputData = new DeleteCourseInputData("Software Design", "CSC207");

        DataAccessInterface courseRepository = new DataAccessInterface();

        // save the course into the data
        courseRepository.saveCourse(course);

        // create a successPresenter that tests if the test cases are like what we expect

        DeleteCourseOutputBoundary sucessPresenter = new DeleteCourseOutputBoundary() {
            @Override
            public void prepareSuccessView(DeleteCourseOutputData deleteCourseOutputData) {
                // TODO: Why is the output data only outputs the course code, not the course object?
                assertEquals(deleteCourseOutputData.getCourseCode(), course.getCode());
            }

            @Override
            public void switchToCourseListView() {
                // This is expected
                // TODO: this test works but we never reach the switch to course list view in the test
                // same thing in lab 5
                // How to reach 100% code coverage
            }
        };
        DeleteCourseInputBoundary interactor = new DeleteCourseInteractor(courseRepository, sucessPresenter, courseFactory);
        interactor.execute(inputData);

        // check that the course no longer exists in the data
        boolean checkExists = courseRepository.existsByCode(course.getCode());
        assertFalse(checkExists);
    }
}
