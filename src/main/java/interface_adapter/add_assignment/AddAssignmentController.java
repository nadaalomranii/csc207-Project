package interface_adapter.add_assignment;

import entity.Course;
import entity.User;
import use_case.add_assignment.AddAssignmentInputBoundary;
import use_case.add_assignment.AddAssignmentInputData;

import java.text.ParseException;
import java.util.Date;

public class AddAssignmentController {
    private final AddAssignmentInputBoundary addAssignmentUseCaseInteractor;

    public AddAssignmentController(AddAssignmentInputBoundary addAssignmentUseCaseInteractor){
        this.addAssignmentUseCaseInteractor = addAssignmentUseCaseInteractor;
    }

    /**
     * Executes the Add Assignment Case.
     * @param name The Assignment name
     * @param dueDate The Assignment due date
     * @param score The score (grade) of the assignment
     * @param weight The weight (in percentage)
     */
    public void execute(String name, Date dueDate, String score, String weight, Course course, User user) {
        final AddAssignmentInputData addAssignmentInputData = new AddAssignmentInputData(
                name, dueDate, score, weight, course, user);

        addAssignmentUseCaseInteractor.execute(addAssignmentInputData);
    }
}
