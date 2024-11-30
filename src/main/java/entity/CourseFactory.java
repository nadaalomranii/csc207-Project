package entity;

/**
 * Factory for creating assignments.
 */
public interface CourseFactory {
    /**
     * Creates a new Course.
     * @param name the name of the new course.
     * @param courseCode the course's code.
     * @return the new course.
     */
    Course create(String name, String courseCode);

}
