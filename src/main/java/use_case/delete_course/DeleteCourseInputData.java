package use_case.delete_course;

import entity.User;

/**
 * The input data for the Delete Course use case.
 */
public class DeleteCourseInputData {
    private final User user;
    private final String courseName;
    private final String courseCode;

    public DeleteCourseInputData(String courseName, String courseCode, User user) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.user = user;
    }

    String getCourseName() {
        return courseName;
    }

    String getCourseCode() {
        return courseCode;
    }

    User getUser() {return user;}
}
