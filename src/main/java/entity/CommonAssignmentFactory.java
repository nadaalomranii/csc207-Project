package entity;

/**
 * Factory for creating CommonAssignment objects.
 */
public class CommonAssignmentFactory implements AssignmentFactory {

    @Override
    public Assignment create(String name, float grade, float weight, String dueDate) {
        return new CommonAssignment(name, grade, weight, dueDate);
    }
}
