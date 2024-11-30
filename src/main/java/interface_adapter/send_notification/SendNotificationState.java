package interface_adapter.send_notification;

import entity.Assignment;
import entity.Course;
import entity.User;

public class SendNotificationState {

    private User user;
    private Course course;
    private Assignment assignment;

    public SendNotificationState() {}

    public User getUser() {return user;}
    public Course getCourse() {return course;}
    public Assignment getAssignment() {return assignment;}

    public void setUser(User user) {this.user = user;}
    public void setCourse(Course course) {this.course = course;}
    public void setAssignment(Assignment assignment) {this.assignment = assignment;}

}
