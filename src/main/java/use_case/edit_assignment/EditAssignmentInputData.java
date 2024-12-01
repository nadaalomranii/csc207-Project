package use_case.edit_assignment;

import entity.Assignment;
import entity.Course;
import entity.User;

import java.util.Date;

/**
 * The input data for the Edit Assignment Use Case.
 */

public class EditAssignmentInputData {

    private final User user;
    private final Course course;
    private final Assignment assignment;
    private  float newScore;
    private  float newWeight;
    private  String newName;
    private  Date newDueDate;


    public EditAssignmentInputData(User user, Course course, Assignment assignment, float newScore, float newWeight,
                                   String newName, Date newDueDate) {
        this.assignment = assignment;
        this.newScore = newScore;
        this.course = course;
        this.user = user;
        this.newWeight = newWeight;
        this.newName = newName;
        this.newDueDate = newDueDate;
    }

    // Getters
    public Assignment getAssignment() {return assignment;}
    public float getNewScore() {return newScore;}
    public String getNewName() {return newName;}
    public Date getNewDueDate() {return newDueDate;}
    public float getNewWeight() {return newWeight;}
    public User getUser() { return this.user; }
    public Course getCourse() { return this.course; }
}
