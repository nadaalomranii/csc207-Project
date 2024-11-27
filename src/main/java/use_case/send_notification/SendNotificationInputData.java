package use_case.send_notification;


import entity.Assignment;
import entity.Course;
import entity.User;

/**
 * The input data for the Send Notification Use Case.
 */
public class SendNotificationInputData {

    private final User user;
    private final Course course;
    private final Assignment assignment;

    public SendNotificationInputData(User user, Course course, Assignment assignment) {
        this.user = user;
        this.course = course;
        this.assignment = assignment;
    }

    public User getUser() { return user; }

    public Assignment getAssignment() {return assignment;}

    public Course getCourse() {
        return course;
    }

}
