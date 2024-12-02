package interface_adapter.edit_assignment;

import entity.Assignment;
import entity.Course;
import entity.User;

import use_case.delete_course.DeleteCourseInputData;
import use_case.edit_assignment.EditAssignmentInputBoundary;
import use_case.edit_assignment.EditAssignmentInputData;

import java.util.Date;

/**
 * Controller for the Edit Assignment Use Case.
 */
public class EditAssignmentController {
    private final EditAssignmentInputBoundary userEditAssignmentUseCaseInteractor;

    public EditAssignmentController(EditAssignmentInputBoundary userEditAssignmentUseCaseInteractor) {
        this.userEditAssignmentUseCaseInteractor = userEditAssignmentUseCaseInteractor;
    }

    /**
     * Executes the edit assignment Use Case.
     * @param newScore the new score
     * @param newWeight the new weight
     * @param newDueDate the new due date
     * @param assignment the assignment that can be edited
     * @param user the user editing
     * @param course the course the assignments in
     */
    public void execute(User user, Course course, Assignment assignment, float newScore, float newWeight, Date newDueDate) {
        final EditAssignmentInputData editAssignmentInputData = new EditAssignmentInputData(user, course, assignment, newScore, newWeight, newDueDate);

        userEditAssignmentUseCaseInteractor.execute(editAssignmentInputData);
    }
}