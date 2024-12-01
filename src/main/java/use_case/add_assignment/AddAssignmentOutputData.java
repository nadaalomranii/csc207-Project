package use_case.add_assignment;

import entity.Course;
import entity.User;

public class AddAssignmentOutputData {
    // TODO: Delete message?
    private final String message;
    private final Course course;
    private final User user;

    public AddAssignmentOutputData(String message, Course course, User user) {
        this.message = message;
        this.course = course;
        this.user = user;
    }

//    public String getMessage() {
//        return message;
//    }

    public Course getCourse() {
        return course;
    }

    public User getUser() { return this.user; }
}
