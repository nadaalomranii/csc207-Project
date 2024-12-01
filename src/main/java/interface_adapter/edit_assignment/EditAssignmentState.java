package src.main.java.interface_adapter.edit_assignment;

import entity.Assignment;

/**
 * The State information representing the assignment.
 */

public class EditAssignmentState {
    private Assignment assignment;

    private float newScore;

    public EditAssignmentState() {

    }

    public Assignment getAssignment() {return assignment;}

    public void setAssignment(Assignment assignment) {this.assignment = assignment;}

    public float getNewScore() {return newScore;}

    public void setNewScore(float newScore) {this.newScore = newScore;}

}
