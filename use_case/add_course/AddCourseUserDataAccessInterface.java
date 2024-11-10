package use_case.add_course;

import entity.Course;

/**
 * The interface of the DAO for the Add Course Use Case.
 */
public interface AddCourseDataAccessInterface {

    /**
     * Updates the system to record this course.
     * @param course is the course to be added
     */
    void addCourse(Course course);
}
