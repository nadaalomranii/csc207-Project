package src.main.java.use_case.edit_assignment;

import entity.Assignment;
import entity.User;

/**
 * The input data for the Edit Assignment Use Case.
 */

public class EditAssignmentInputData {

    private final Assignment assignment;
    private final float newScore;
    private final User user;

    public EditAssignmentInputData(Assignment assignment, float newScore, User user) {
        this.assignment = assignment;
        this.newScore = newScore;
        this.user = user;
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

    public User getUser() { return this.user; }
}
