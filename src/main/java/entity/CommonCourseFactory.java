package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory for creating CommonCourse objects.
 */
public class CommonCourseFactory implements CourseFactory {

    @Override
    public Course create(String name, String courseCode, List<Assignment> assignments) {
        return new CommonCourse(name, courseCode, assignments);
    }
}
