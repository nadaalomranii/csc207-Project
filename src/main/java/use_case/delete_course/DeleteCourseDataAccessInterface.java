package use_case.delete_course;

import entity.Course;
import entity.User;

import java.util.List;

public interface DeleteCourseDataAccessInterface {
    /**
     * Deletes the course.
     * @param course the course to delete
     */
    void deleteCourse(Course course, User user);

    /**
     * Generate the list of courses from the user.
     * @param user the user to get courses from
     */
    List<Course> getCourses(User user);
}
