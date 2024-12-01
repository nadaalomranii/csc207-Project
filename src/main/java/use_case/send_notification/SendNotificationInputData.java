package use_case.send_notification;


import entity.Assignment;
import entity.Course;
import entity.User;

import java.util.List;

/**
 * The input data for the Send Notification Use Case.
 */
public class SendNotificationInputData {

    private final User user;
    private final Course course;
    private final List<Assignment> assignments;

    public SendNotificationInputData(User user, Course course, List<Assignment> assignments) {
        this.user = user;
        this.course = course;
        this.assignments = assignments;
    }

    public User getUser() { return user; }

    public List<Assignment> getAssignments() {return assignments;}

    public Course getCourse() {
        return course;
    }

}
