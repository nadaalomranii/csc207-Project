package entity;

import java.util.List;

/**
 * An implementation of the Course interface.
 */
public class CommonCourse implements Course {
    private final String name;
    private final String courseCode;
    private final List<Assignment> assignments;

    public CommonCourse(String name, String courseCode, List<Assignment> assignments) {
        this.name = name;
        this.courseCode = courseCode;
        this.assignments = assignments;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCode() {
        return courseCode;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }
}
