package use_case.delete_course;

import entity.User;

public class DeleteCourseOutputData {
    private final String courseCode;
    private final User user;

    public DeleteCourseOutputData(String courseCode, User user) {
        this.courseCode = courseCode;
        this.user = user;
    }

    public String getCourseCode() { return courseCode; }

    public User getUser() { return user; }

}
