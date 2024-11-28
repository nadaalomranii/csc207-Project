package use_case.edit_assignment;

import entity.Assignment;

public interface EditAssignmentDataAccessInterface {
    /**
     * Checks if the course already exists.
     * @param name the assignment name to look for
     * @return true if a course with the given code exists; false otherwise
     */
    boolean existsByName(String name);
    /**
     * Updates the system to record this assignment's score.
     * @param assignment the assignment whose score is to be updated
     */
    void editAssignment(Assignment assignment);
}
