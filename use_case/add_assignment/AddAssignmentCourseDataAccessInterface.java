package use_case.add_assignment;

import entity.Assignment;
import entity.Course;

public interface AddAssignmentCourseDataAccessInterface {
    void saveAssignment(Assignment assignment, Course course);
}
