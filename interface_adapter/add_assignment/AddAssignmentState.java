package interface_adapter.add_assignment;

import entity.Course;

public class AddAssignmentState {
    private String assignmentName;
    private String dueDate;
    private String grade;
    private String weight;
    private Course course;

    public AddAssignmentState() {

    }

    public String getAssignmentName() {return assignmentName;}

    public void setAssignmentName(String assignmentName) {this.assignmentName = assignmentName;}

    public String getDueDate() {return dueDate;}

    public void setDueDate(String dueDate) {this.dueDate = dueDate;}

    public String getGrade() {return grade;}

    public void setGrade(String score) {this.grade = score;}

    public String getWeight() {return this.weight;}

    public void setWeight(String score) {this.weight = score;}

    public void setCourse(Course course) {this.course = course;}

    public Course getCourse() {return course;}
}
