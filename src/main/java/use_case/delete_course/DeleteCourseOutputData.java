package use_case.delete_course;

public class DeleteCourseOutputData {
    private final String courseCode;

    public DeleteCourseOutputData(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseCode() {
        return courseCode;
    }
}
