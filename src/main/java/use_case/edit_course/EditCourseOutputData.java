package use_case.edit_course;

public class EditCourseOutputData {
    private final String courseName;
    private final String courseCode;

    public EditCourseOutputData(String courseName, String courseCode) {
        this.courseName = courseName;
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }
}
