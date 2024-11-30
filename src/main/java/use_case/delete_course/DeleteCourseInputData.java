package use_case.delete_course;

/**
 * The input data for the Delete Course use case.
 */
public class DeleteCourseInputData {
    private final String courseName;
    private final String courseCode;

    public DeleteCourseInputData(String courseName, String courseCode) {
        this.courseName = courseName;
        this.courseCode = courseCode;
    }

    String getCourseName() {
        return courseName;
    }

    String getCourseCode() {
        return courseCode;
    }
}
