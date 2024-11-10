package interface_adapter.add_assignment;

import use_case.add_assignment.AddAssignmentInputBoundary;
import use_case.add_assignment.AddAssignmentInputData;

/**
 * Controller for the Add Assignment Use Case.
 */
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
    public void execute(String name, String dueDate, double score, double weight) {
        final AddAssignmentInputData addAssignmentInputData = new AddAssignmentInputData(
                name, dueDate, score, weight);

        addAssignmentUseCaseInteractor.execute(addAssignmentInputData);
    }

    /**
     * Executes the "switch to assignmentView" Use case.
     */
    public void switchToAssignmentView() { addAssignmentUseCaseInteractor.switchToAssignmentView();}
}
