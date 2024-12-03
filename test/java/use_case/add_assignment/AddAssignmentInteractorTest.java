package use_case.add_assignment;

import com.github.weisj.jsvg.nodes.Use;
import data_access.DataAccessInterface;
import entity.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AddAssignmentInteractorTest {
    @Test
    void successTest() {
        CourseFactory factory = new CommonCourseFactory();
        UserFactory userFactory = new CommonUserFactory();
        AssignmentFactory assignmentFactory = new CommonAssignmentFactory();
        // Create an empty course
        Course course = factory.create("Software Design", "CSC207", new ArrayList<>());
        User user = userFactory.create("Nada", "nadaalomrani", "password", "nada@gmail.com");

        // Create input Data for the assignment
        AddAssignmentInputData inputData = new AddAssignmentInputData("Course Project",
                new Date(2024, Calendar.DECEMBER, 3),
                "100",
                "25",
                course,
                user);
        DataAccessInterface courseRepository = new DataAccessInterface();

        // For the success test, we need to add the course to the data access repository before we add the assignment
        courseRepository.save(user);
        courseRepository.saveCourse(course, user);

        // create a successPresenter that tests if the test cases are like what we expect
        AddAssignmentOutputBoundary successPresenter = new AddAssignmentOutputBoundary() {
            @Override
            public void prepareSuccessView(EditAssignmentOutputData outputData) {
                assertEquals(outputData.getCourse(), course);
                assertEquals(outputData.getUser(), user);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected");
            }
        };
        AddAssignmentInputBoundary interactor = new AddAssignmentInteractor(courseRepository, successPresenter, assignmentFactory);
        interactor.execute(inputData);
    }

    @Test
    void failTest() {
        CourseFactory factory = new CommonCourseFactory();
        UserFactory userFactory = new CommonUserFactory();
        AssignmentFactory assignmentFactory = new CommonAssignmentFactory();

        // Create a course
        Course course = factory.create("Software Design", "CSC207", new ArrayList<>());
        // Create a user
        User user = userFactory.create("Nada", "nadaalomrani", "password", "nada@gmail.com");

        // Create an assignment to add to user
        Assignment assignment = assignmentFactory.create("Course Project", 100, 25, new Date(2024, Calendar.DECEMBER, 3));

        // Create input Data for the assignment
        AddAssignmentInputData inputData = new AddAssignmentInputData("Course Project",
                new Date(2024, Calendar.DECEMBER, 3),
                "100",
                "25",
                course,
                user);
        DataAccessInterface courseRepository = new DataAccessInterface();

        // For the fail test, we need to add the assignment to the DAO and make sure that a new assignment with the same name is not added
        courseRepository.save(user);
        courseRepository.saveCourse(course, user);
        courseRepository.saveAssignment(assignment, course, user);

        AddAssignmentOutputBoundary successPresenter = new AddAssignmentOutputBoundary() {
            @Override
            public void prepareSuccessView(EditAssignmentOutputData outputData) {
                fail("Use case success is unexpected");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                // It is expected that the use case fails, so as long as we don't reach the success test,
                // this use case has been implemented correctly
            }
        };
        AddAssignmentInputBoundary interactor = new AddAssignmentInteractor(courseRepository, successPresenter, assignmentFactory);
        interactor.execute(inputData);
    }
}
