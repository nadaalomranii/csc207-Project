package use_case.delete_assignment;

import entity.Course;
import entity.User;

public interface DeleteAssignmentDataAccessInterface {
    void deleteAssignment(String assignmentName, Course course, User user);
}

