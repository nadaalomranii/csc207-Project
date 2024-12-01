package entity;

import java.util.Date;

/**
 * An implementation of the Assignment interface.
 */
public class CommonAssignment implements Assignment {
    private final String name;
    private float grade;
    private final float weight;
    private final Date dueDate;

    public CommonAssignment(String name, float grade, float weight, Date dueDate) {
        this.name = name;
        this.grade = grade;
        this.weight = weight;
        this.dueDate = dueDate;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getGrade() {
        return grade;
    }

    @Override
    public float getWeight() {
        return weight;
    }

    @Override
    public Date getDueDate() {
        return dueDate;
    }

    // setter
    public void updateGrade(float newGrade) {
        this.grade = newGrade;
    }
}
