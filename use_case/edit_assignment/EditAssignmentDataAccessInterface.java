package use_case.edit_assignment;

import entity.Assignment;

public interface EditAssignmentDataAccessInterface {
    /**
     * Updates the system to record this assignment's score.
     * @param assignment the assignment whose score is to be updated
     */
    void editAssignment(Assignment assignment);
}
