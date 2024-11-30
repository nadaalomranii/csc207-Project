package entity;

import java.util.Date;

/**
 * Factory for creating CommonAssignment objects.
 */
public class CommonAssignmentFactory implements AssignmentFactory {

    @Override
    public Assignment create(String name, float grade, float weight, Date dueDate) {
        return new CommonAssignment(name, grade, weight, dueDate);
    }
}
