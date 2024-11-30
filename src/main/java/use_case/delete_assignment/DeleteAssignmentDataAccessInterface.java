package use_case.delete_assignment;

import entity.Course;

public interface DeleteAssignmentDataAccessInterface {
    void deleteAssignment(String assignmentName, Course course);
}

