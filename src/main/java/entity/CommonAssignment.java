package entity;

import java.util.Date;

/**
 * An implementation of the Assignment interface.
 */
public class CommonAssignment implements Assignment {
    private final String name;
    private float grade;
    private float weight;
    private Date dueDate;
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
    public float getGrade() {return grade;}

    @Override
    public void changeGrade(float newGrade) {this.grade = newGrade;}

    @Override
    public float getWeight() {
        return weight;
    }

    @Override
    public void changeWeight(float newWeight) {this.weight = newWeight;}

    @Override
    public Date getDueDate() {return dueDate;}

    @Override
    public void changeDueDate(Date newDueDate) {this.dueDate = newDueDate;}

    @Override
    public boolean isScheduled() {return scheduled;}

    public void setScheduled(boolean scheduled) {this.scheduled = scheduled;}
}
