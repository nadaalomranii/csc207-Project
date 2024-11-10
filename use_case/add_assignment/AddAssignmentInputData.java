package use_case.add_assignment;

/**
 * The input data for the Add Assignment Use Case.
 */
public class AddAssignmentInputData {

    private final String name;
    private final String dueDate;
    private final double score;
    private final double weight;
    private final String courseCode;

    public AddAssignmentInputData(String name, String dueDate, double score, double weight, String courseCode) {
        this.name = name;
        this.dueDate = dueDate;
        this.score = score;
        this.weight = weight;
        this.courseCode = courseCode;
    }

    // Getters
    public String getName() {
        return name;
    }
    public String getDueDate() {
        return dueDate;
    }
    public double getScore() {
        return score;
    }
    public double getWeight() {
        return weight;
    }
    public String getCourseCode(){ return courseCode;}
}
