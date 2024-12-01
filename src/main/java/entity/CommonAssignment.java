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
    private boolean scheduled;

    public CommonAssignment(String name, float grade, float weight, Date dueDate) {
        this.name = name;
        this.grade = grade;
        this.weight = weight;
        this.dueDate = dueDate;
        this.scheduled = false;
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

    @Override
    public boolean isScheduled() {return scheduled;}
    public void setScheduled(boolean scheduled) {this.scheduled = scheduled;}
}
