package entity;

import java.util.List;

/**
 * Factory for creating assignments.
 */
public interface CourseFactory {
    /**
     * Creates a new Course.
     * @param name the name of the new course.
     * @param assignments the list of assignments.
     * @return the new course.
     */
    Course create(String name, List<Assignment> assignments);

}
