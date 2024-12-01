package use_case.add_assignment;

import entity.Assignment;
import entity.Course;
import entity.User;

public interface AddAssignmentDataAccessInterface {
    /**
     * Checks if the course already exists.
     * @param name the assignment name to look for
     * @return true if a course with the given code exists; false otherwise
     */
    boolean existsByName(String name);

    void saveAssignment(Assignment assignment, Course course, User user);
}
