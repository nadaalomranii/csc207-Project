package use_case.edit_assignment;

import entity.Assignment;
import java.util.Date;


/**
 * The input data for the Edit Assignment Use Case.
 */

public class EditAssignmentInputData {

    private final String name;
    private final float newScore;
    private final float weight;
    private final Date dueDate;

    public EditAssignmentInputData(String name, float newScore, float weight, Date dueDate) {
        this.name = name;
        this.newScore = newScore;
        this.weight = weight;
        this.dueDate = dueDate;
    }

    // Getters
    public String getName() {

        return name;
    }

    public float getNewScore() {

        return newScore;
    }

    public float getWeight() {

        return weight;
    }

    public Date getDueDate() {

        return dueDate;
    }

}
