package interface_adapter.add_course;

public class AddCourseState {
    private String courseName;
    private String courseCode;
    private String AddCourseError;

    public AddCourseState() {}

    public String getCourseName() { return courseName; }

    public String getCourseCode() { return courseCode; }

    public void setCourseName(String courseName) { this.courseName = courseName; }

    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public void setAddCourseError(String error) { this.AddCourseError = error; }
}
