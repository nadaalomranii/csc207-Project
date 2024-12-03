package use_case.delete_course;

import entity.Course;
import entity.User;

import java.util.List;

public class DeleteCourseOutputData {
    private final String courseCode;
    private final User user;
    private final List<Course> courses;

    public DeleteCourseOutputData(String courseCode, User user, List<Course> courses) {
        this.courseCode = courseCode;
        this.user = user;
        this.courses = courses;
    }

    public String getCourseCode() { return courseCode; }

    public User getUser() { return user; }

    public List<Course> getCourses() {
        return courses;
    }
}
