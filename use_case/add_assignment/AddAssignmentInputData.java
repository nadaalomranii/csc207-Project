package use_case.add_assignment;

/**
 * The input data for the Add Assignment Use Case.
 */
public class AddAssignmentInputData {

    private final String name;
    private final String dueDate;
    private final float score;
    private final float weight;
    private final String courseCode;

    public AddAssignmentInputData(String name, String dueDate, String score, String weight, String courseCode) {
        this.name = name;
        this.dueDate = dueDate;
        this.score = Float.parseFloat(score);
        this.weight = Float.parseFloat(weight);
        this.courseCode = courseCode;
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
    public String getCourseCode() {
        return courseCode;
    }
}
