package use_case.add_assignment;

import entity.Course;

/**
 * The input data for the Add Assignment Use Case.
 */
public class AddAssignmentInputData {

    private final String name;
    private final String dueDate;
    private final float score;
    private final float weight;
    private final Course course;

    public AddAssignmentInputData(String name, String dueDate, String score, String weight, Course course) {
        this.name = name;
        this.dueDate = dueDate;
        this.score = Float.parseFloat(score);
        this.weight = Float.parseFloat(weight);
        this.course = course;
    }

    // Getters
    public String getName() {
        return name;
    }
    public String getDueDate() {
        return dueDate;
    }
    public float getScore() {
        return score;
    }
    public float getWeight() {
        return weight;
    }
    public Course getCourse() {return course;}
}
