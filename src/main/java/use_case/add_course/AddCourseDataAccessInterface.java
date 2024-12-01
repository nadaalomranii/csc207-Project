package use_case.add_course;

import entity.Assignment;
import entity.Course;
import entity.User;

import java.util.List;
import java.util.Map;

public interface AddCourseDataAccessInterface {
    /**
     * Checks if the course already exists.
     * @param code the course code to look for
     * @return true if a course with the given code exists; false otherwise
     */
    boolean existsByCode(String code, User user);

    /**
     * Saves the course.
     * @param course the course to save
     */
    void saveCourse(Course course, User user);

    List<Course> getCourses(User user);
}


