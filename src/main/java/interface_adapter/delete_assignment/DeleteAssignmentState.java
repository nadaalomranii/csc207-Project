package interface_adapter.delete_assignment;

import entity.Course;

public class DeleteAssignmentState {
    private String assignmentName;
    private Course course;

    public DeleteAssignmentState(String assignmentName, Course code) {}

    public String getAssignmentName() {return assignmentName;}
    public Course getCourse() {return course;}
}
