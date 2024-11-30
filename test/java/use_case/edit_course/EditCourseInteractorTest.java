package use_case.edit_course;

import data_access.DataAccessInterface;
import entity.*;
import interface_adapter.edit_course.EditCoursePresenter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class EditCourseInteractorTest {
    @Test
    void successTest() {
        CourseFactory courseFactory = new CommonCourseFactory();

        Course course = courseFactory.create("Software", "csc207");

        EditCourseInputData inputData = new EditCourseInputData("Software Design", "csc207");

        DataAccessInterface courseRepository = new DataAccessInterface();

        courseRepository.saveCourse(course);

        EditCourseOutputBoundary successPresenter = new EditCourseOutputBoundary() {
            @Override
            public void prepareSuccessView(EditCourseOutputData editCourseOutputData) {
                assertEquals(editCourseOutputData.getCourseName(), courseRepository.checkName(inputData.getCourseCode()));
            }

            @Override
            public void prepareFailView() {
                // TODO:
            }

            @Override
            public void switchToAssignmentListView() {
                // TOOD:
            }
        };

        EditCourseInputBoundary interactor = new EditCourseInteractor(courseRepository, successPresenter, courseFactory);
        interactor.execute(inputData);

        // Check that the course name has been changed
        assertEquals(inputData.getCourseName(), courseRepository.checkName(inputData.getCourseCode()));
    }
}
