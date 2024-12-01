package use_case.edit_assignment;

import entity.Assignment;
import entity.Course;
import entity.User;

public interface EditAssignmentDataAccessInterface {
    /**
     * Checks if the course already exists.
     * @param name the assignment name to look for
     * @return true if a course with the given code exists; false otherwise
     */
    boolean existsByName(String name, Course course, User user);
    /**
     * Updates the system to record this assignment's score.
     * @param assignment the assignment whose score is to be updated
     */
    void editAssignment(Assignment assignment, Course course);
}
