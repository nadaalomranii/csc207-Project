package use_case.edit_course;

import entity.Course;
import entity.User;

/**
* The data access interface for the edit course use case.
 */
public interface EditCourseDataAccessInterface {
    void editCourse(Course course, User user);

    // Return the course name from the course code.
    String checkName(String courseCode, User user);
}
