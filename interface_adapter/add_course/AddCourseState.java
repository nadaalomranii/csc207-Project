package interface_adapter.add_course;

public class AddCourseState {
    private String courseName;
    private int courseCode;

    public String getCourseName() { return courseName; }

    public int getCourseCode() { return courseCode; }

    public void setCourseName(String courseName) { this.courseName = courseName; }

    public void setCourseCode(int courseCode) { this.courseCode = courseCode; }
}
