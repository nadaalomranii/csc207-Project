package use_case.add_assignment;

import entity.Course;

public class AddAssignmentOutputData {
    // TODO: Delete message?
    private final String message;
    private final Course course;

    public AddAssignmentOutputData(String message, Course course) {
        this.message = message;
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }
}
