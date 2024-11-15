package use_case.add_assignment;

import entity.Course;

public class AddAssignmentOutputData {
    private final String message;
    private final Course course;

    public AddAssignmentOutputData(String message, Course course) {
        this.message = message;
        this.course = course;
    }

    public String getMessage() {
        return message;
    }

    public Course getCourse() {
        return course;
    }
}
