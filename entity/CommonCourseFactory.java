package entity;

import java.util.List;

/**
 * Factory for creating CommonCourse objects.
 */
public class CommonCourseFactory implements CourseFactory {

    @Override
    public Course create(String name, List<Assignment> assignments) {
        return new CommonCourse(name, assignments);
    }
}
