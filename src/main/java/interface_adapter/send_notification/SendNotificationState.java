package interface_adapter.send_notification;

import entity.Assignment;
import entity.Course;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class SendNotificationState {

    private User user;
    private Course course;
    private List<Assignment> assignments;
    private List<Assignment> newlyScheduledAssignments = new ArrayList<>();

    public SendNotificationState() {}

    public User getUser() {return user;}
    public Course getCourse() {return course;}
    public List<Assignment> getAssignments() {return assignments;}

    public List<Assignment> getNewlyScheduledAssignments() {return newlyScheduledAssignments; }
    public void setNewlyScheduledAssignments(List<Assignment> newlyScheduledAssignments) { this.newlyScheduledAssignments = newlyScheduledAssignments; }

    // TODO: do we actually need these?
//    public void setUser(User user) {this.user = user;}
//    public void setCourse(Course course) {this.course = course;}
//    public void setAssignment(Assignment assignment) {this.assignments = assignments;}

}
