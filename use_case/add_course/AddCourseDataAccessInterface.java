package use_case.add_course;

import entity.Course;

public interface AddCourseDataAccessInterface {
    /**
     * Checks if the course already exists.
     * @param code the course code to look for
     * @return true if a course with the given code exists; false otherwise
     */
    boolean existsByCode(String code);

    /**
     * Saves the course.
     * @param course the course to save
     */
    void save(Course course);
}


