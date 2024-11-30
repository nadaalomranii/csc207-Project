package use_case.edit_course;

import entity.Course;
/**
* The data access interface for the edit course use case.
 */
public interface EditCourseDataAccessInterface {
    void editCourse(Course course);
}
