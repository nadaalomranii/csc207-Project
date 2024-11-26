package use_case.add_assignment;

import entity.Course;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * The input data for the Add Assignment Use Case.
 */
public class AddAssignmentInputData {

    private final String name;
    private final Date dueDate;
    private final float score;
    private final float weight;
    private final Course course;

    public AddAssignmentInputData(String name, Date dueDate, String score, String weight, Course course) {
    //throws ParseException {
        this.name = name;
        //SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        //this.dueDate = formatter.parse(dueDate);
        this.dueDate = dueDate;
        this.score = Float.parseFloat(score);
        this.weight = Float.parseFloat(weight);
        this.course = course;
    }

    // Getters
    public String getName() {
        return name;
    }
    public Date getDueDate() {
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

// changed due date to Date Type, and changed getDueDate to return Date type instead of String (k love you, Miral)