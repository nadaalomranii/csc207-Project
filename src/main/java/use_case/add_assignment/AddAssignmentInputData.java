package use_case.add_assignment;

import entity.Course;
import entity.User;

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
    private final User user;

    public AddAssignmentInputData(String name, Date dueDate, String score, String weight, Course course, User user) {
    //throws ParseException {
        this.name = name;
        //SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        //this.dueDate = formatter.parse(dueDate);
        this.dueDate = dueDate;
        this.score = Float.parseFloat(score);
        this.weight = Float.parseFloat(weight);
        this.course = course;
        this.user = user;
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
    public User getUser() {return user;}
}

// changed due date to Date Type, and changed getDueDate to return Date type instead of String (k love you, Miral)
