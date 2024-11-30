package use_case.edit_course;

/**
 * The input data for the edit course use case.
 */
public class EditCourseInputData {
    private final String courseName;
    private final String courseCode;

    public EditCourseInputData(String courseName, String courseCode) {
        this.courseName = courseName;
        this.courseCode = courseCode;
    }

    String getCourseName() {return courseName;}

    String getCourseCode() {return courseCode;}
}
