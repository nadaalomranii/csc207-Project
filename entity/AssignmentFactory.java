package entity;

/**
 * Factory for creating assignments.
 */
public interface AssignmentFactory {
    /**
     * Creates a new Assignment.
     * @param name the name of the new assignment
     * @param grade the grade of the assignment
     * @param weight the weight of the assignment
     * @param dueDate the due date of the assignment
     * @return the new assignment
     */
    Assignment create(String name, float grade, float weight, String dueDate);

}
