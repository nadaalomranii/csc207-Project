package use_case.edit_assignment;

import entity.Assignment;

/**
 * The input data for the Edit Assignment Use Case.
 */

public class EditAssignmentInputData {

    private final Assignment assignment;
    private final float newScore;

    public EditAssignmentInputData(Assignment assignment, float newScore) {
        this.assignment = assignment;
        this.newScore = newScore;
    }

    // Getters
    public Assignment getAssignment() {

        return assignment;
    }
    public float getNewScore() {

        return newScore;
    }

    public String getName() {
        return assignment.getName();
    }
}
