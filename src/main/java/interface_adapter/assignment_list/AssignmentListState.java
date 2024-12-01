package interface_adapter.assignment_list;

import entity.Assignment;
import entity.Course;
import entity.User;

import java.util.List;

public class AssignmentListState {
    private List<Assignment> assignmentList;
    private Course course;
    private User user;


    public void setCourse(Course course) {
        this.course = course;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() { return this.course;}
    public User getUser() { return this.user;}
    public List<Assignment> getAssignmentList() { return this.assignmentList;}

    public void setAssignmentList(List<Assignment> assignmentList) {this.assignmentList = assignmentList;}

    public void addAssignment(Assignment assignment) {
        this.assignmentList.add(assignment);
    }
}
