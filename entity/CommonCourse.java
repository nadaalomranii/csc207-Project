package entity;

import java.util.List;

/**
 * An implementation of the Course interface.
 */
public class CommonCourse implements Course{
    private final String name;
    private final List<Assignment> assignments;

    public CommonCourse(String name, List<Assignment> assignments) {
        this.name = name;
        this.assignments = assignments;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Assignment> getAssignments() {
        return assignments;
    }
}
