package use_case.delete_assignment;

import data_access.DataAccessInterface;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.assignment_list.AssignmentListViewModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteAssignmentInteractorTest {
    @Test
    void successTest() {
        CourseFactory factory = new CommonCourseFactory();
        UserFactory userFactory = new CommonUserFactory();
        AssignmentFactory assignmentFactory = new CommonAssignmentFactory();

        // Create an empty course
        Course course = factory.create("Software Design", "CSC207", new ArrayList<>());
        User user = userFactory.create("Nada", "nadaalomrani", "password", "nada@gmail.com");
        Assignment assignment = assignmentFactory.create("Course Project", 100, 25, new Date(2024, Calendar.DECEMBER, 3));

        DataAccessInterface courseRepository = new DataAccessInterface();

        // For the success test, we need to add the course to the data access repository before we add the assignment
        courseRepository.save(user);
        courseRepository.saveCourse(course, user);
        courseRepository.saveAssignment(assignment, course, user);

        // create a successPresenter that tests if the test cases are like what we expect
        DeleteAssignmentOutputBoundary successPresenter = new DeleteAssignmentOutputBoundary() {
            @Override
            public void prepareSuccessView(DeleteAssignmentOutputData outputData) {
                assert (!courseRepository.assignmentExistsByName(("Software Design"), course, user));
                assert (outputData.getAssignmentName().equals("Software Design"));
            }

            @Override
            public void prepareFailView(DeleteAssignmentOutputData deleteAssignmentOutputData) {
                assert(courseRepository.assignmentExistsByName(("Software Design"), course, user));
            }
        };

        DeleteAssignmentInputData inputData = new DeleteAssignmentInputData("Software Design", course, user);
        DeleteAssignmentInteractor interactor = new DeleteAssignmentInteractor(courseRepository, successPresenter);
        interactor.execute(inputData);
        }
    }
