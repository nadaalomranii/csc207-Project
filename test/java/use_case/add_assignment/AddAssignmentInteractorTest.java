package use_case.add_assignment;

import data_access.DataAccessInterface;
import entity.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

class AddAssignmentInteractorTest {
    @Test
    void successTest() {
        CourseFactory factory = new CommonCourseFactory();
        AssignmentFactory assignmentFactory = new CommonAssignmentFactory();
        Course course = factory.create("Software Design", "CSC207");

        Date date = new Date(2024, 12, 1);
        AddAssignmentInputData inputData = new AddAssignmentInputData("Course Project",
                date,
                "100",
                "25", course);
        DataAccessInterface courseRepository = new DataAccessInterface();

        // For the success test, we need to add the course to the data access repository before we add the assignment
        // TODO: rename DataAccessInterface to DataAccessObject
        courseRepository.saveCourse(course);

        // create a successPresenter that tests if the test cases are like what we expect
        AddAssignmentOutputBoundary successPresenter = new AddAssignmentOutputBoundary() {
            @Override
            public void prepareSuccessView(AddAssignmentOutputData outputData) {
                assertEquals(outputData.getCourse(), course);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected");
            }
        };
        AddAssignmentInputBoundary interactor = new AddAssignmentInteractor(courseRepository, successPresenter, assignmentFactory);
        interactor.execute(inputData);
    }
}