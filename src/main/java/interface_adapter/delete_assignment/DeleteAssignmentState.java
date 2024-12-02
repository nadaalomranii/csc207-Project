package interface_adapter.delete_assignment;

import entity.Course;
import entity.User;

public class DeleteAssignmentState {
    private User user;
    private String assignmentName;
    private Course course;

    public DeleteAssignmentState(String assignmentName, Course code) {}

    public String getAssignmentName() {return assignmentName;}
    public Course getCourse() {return course;}
    public User getUser() {return user;}
}
