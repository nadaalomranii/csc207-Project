package use_case.add_assignment;

import entity.Assignment;
import entity.Course;
import entity.User;

public interface AddAssignmentCourseDataAccessInterface {
    // TODO: FOR TESTING - add an Assignment Exists method (Takes in the course and assignment name)
    void saveAssignment(Assignment assignment, Course course, User user);
}
