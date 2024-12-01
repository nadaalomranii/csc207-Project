package use_case.edit_course;

import entity.User;

/**
 * The input data for the edit course use case.
 */
public class EditCourseInputData {
    private final String courseName;
    private final String courseCode;
    private final User user;

    public EditCourseInputData(String courseName, String courseCode, User user) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.user = user;
    }

    String getCourseName() {return courseName;}

    String getCourseCode() {return courseCode;}

    User getUser() {return user;}
}
