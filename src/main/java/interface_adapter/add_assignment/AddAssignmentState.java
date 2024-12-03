package interface_adapter.add_assignment;

import entity.Course;
import entity.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddAssignmentState {
    private String assignmentName;
    private Date dueDate;
    private String grade;
    private String weight;
    private Course course;
    private User user;

    public String getAssignmentName() {return assignmentName;}

    public void setAssignmentName(String assignmentName) {this.assignmentName = assignmentName;}

    public Date getDueDate() {return dueDate;}

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getGrade() {return grade;}

    public void setGrade(String score) {this.grade = score;}

    public String getWeight() {return this.weight;}

    public void setWeight(String score) {this.weight = score;}

    public void setCourse(Course course) {this.course = course;}

    public Course getCourse() {return course;}

    public void setUser(User user) {this.user = user;}

    public User getUser() {return user;}
}
