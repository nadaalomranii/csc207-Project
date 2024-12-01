package interface_adapter.delete_course;

import entity.User;

public class DeleteCourseState {
    private String courseName;
    private String courseCode;
    private User user;

    public String getCourseCode() {
        return courseCode;
    }
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public User getUser() { return user;}
    public void setUser(User user) { this.user = user;}
}
