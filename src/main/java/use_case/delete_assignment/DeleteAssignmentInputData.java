package use_case.delete_assignment;

import entity.Course;

/**
 * The input data for the Delete Assignment use case.
 */
public class DeleteAssignmentInputData {
    private final String assignmentName;
    private final Course course;

    public DeleteAssignmentInputData(String assignmentName, Course course) {
        this.assignmentName = assignmentName;
        this.course = course;
    };

    public String getAssignmentName() { return assignmentName; }
    public Course getCourse() { return course; }
}
