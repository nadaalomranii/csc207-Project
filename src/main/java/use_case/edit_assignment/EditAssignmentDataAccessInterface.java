package use_case.edit_assignment;

import entity.Assignment;
import entity.Course;
import entity.User;

import java.util.Date;

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
    void editAssignment(Assignment assignment, Course course, User user);

    void changeScore(Assignment assignment, float newScore, Course course, User user);

    void changeDate(Assignment assignment, Date newDueDate, Course course, User user);

    void changeWeight(Assignment assignment, float newWeight, Course course, User user);
}
