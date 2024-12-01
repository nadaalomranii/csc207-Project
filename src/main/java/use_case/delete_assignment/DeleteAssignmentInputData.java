package use_case.delete_assignment;

import entity.Course;
import entity.User;

/**
 * The input data for the Delete Assignment use case.
 */
public class DeleteAssignmentInputData {
    private final String assignmentName;
    private final Course course;
    private final User user;

    public DeleteAssignmentInputData(String assignmentName, Course course, User user) {
        this.assignmentName = assignmentName;
        this.course = course;
        this.user = user;
    };

    public String getAssignmentName() { return assignmentName; }
    public Course getCourse() { return course; }
    public User getUser() { return user; }
}
