package use_case.add_assignment;

import entity.Assignment;
import entity.Course;
import entity.User;

public interface AddAssignmentDataAccessInterface {
    /**
     * Checks if the course already exists.
     * @param name the assignment name to look for
     * @return true if a course with the given code exists; false otherwise
     * @param course the course in which to check for the assignment
     * @param user the user associated with the course and assignment
     */
    boolean assignmentExistsByName(String name, Course course, User user);

    void saveAssignment(Assignment assignment, Course course, User user);
}
