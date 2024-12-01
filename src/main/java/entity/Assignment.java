package entity;

import java.util.Date;

/**
 * The representation of an assignment in our program.
 */
public interface Assignment {

    /**
     * Returns the name of the assignment.
     * @return the name of the assignment.
     */
    String getName();

    /**
     * Returns the grade of the assignment.
     * @return the grade of the assignment.
     */
    float getGrade();

    /**
     * Returns the weighting of the assignment.
     * @return the weighting of the assignment.
     */
    float getWeight();

    /**
     * @return the due date of the assignment.
     */
    Date getDueDate();

    /**
     * @return whether a notification has already been scheduled for this assignment.
     */
    boolean isScheduled();

    void setScheduled(boolean scheduled);
}
