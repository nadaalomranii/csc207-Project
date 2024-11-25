package use_case.add_course;

import data_access.DataAccessInterface;
import entity.*;
import org.junit.jupiter.api.Test;
import use_case.add_course.AddCourseInputData;
import use_case.add_course.AddCourseOutputBoundary;

import static org.junit.jupiter.api.Assertions.*;

class AddCourseInteractorTest {
    @Test
    void successTest() {
        CourseFactory factory = new CommonCourseFactory();
        AddCourseInputData courseInputData = new AddCourseInputData("Software Design", "CSC207");
        Course course = factory.create(courseInputData.getName(), courseInputData.getCode());
        DataAccessInterface courseRepository = new DataAccessInterface();

        // For the success test, we need to add the course to the data access repository before we add the assignment
        courseRepository.saveCourse(course);

        // create a successPresenter that tests if the test cases are like what we expect
        AddCourseOutputBoundary successPresenter = new AddCourseOutputBoundary() {
            @Override
            public void prepareSuccessView(AddCourseOutputData outputData) {
                assertEquals(outputData.getCourse(), course);
                assertEquals(outputData.getCode(), course.getCode());
                assertEquals(outputData.getName(), course.getName());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected");
            }

            @Override
            public void switchToAssignmentView() {
            }
        };
        AddCourseInputBoundary interactor = new AddCourseInteractor(courseRepository, successPresenter, factory);
        interactor.execute(courseInputData);
    }
}
