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
     * Change the grade of the assignment
     */
    void changeGrade(float newGrade);

    /**
     * Returns the weighting of the assignment.
     * @return the weighting of the assignment.
     */
    float getWeight();

    /**
     * Change the weighting of the assignment
     */
    void changeWeight(float newWeight);

    /**
     * @return the due date of the assignment.
     */
    Date getDueDate();

    /**
     * Change the due date of the assignment
     */
    void changeDueDate(Date newDueDate);

    /**
     * @return whether a notification has already been scheduled for this assignment.
     */
    boolean isScheduled();

    void setScheduled(boolean scheduled);
}
