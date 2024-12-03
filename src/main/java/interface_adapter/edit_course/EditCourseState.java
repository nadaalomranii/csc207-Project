package interface_adapter.edit_course;

import entity.User;

/**
 * The state for the edit course view model.
 */
public class EditCourseState {
    private String courseName = "";
    private String courseCode = "";
    private User user;
    private String editError;

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

    public void setUser(User user) { this.user = user; }

    public User getUser() { return user; }

    public String getEditError() {
        return editError;
    }

    public void setEditError(String editError) {
        this.editError = editError;
    }
}
