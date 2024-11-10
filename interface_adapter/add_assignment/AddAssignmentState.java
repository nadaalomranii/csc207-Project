package interface_adapter.add_assignment;

public class AddAssignmentState {
    private String assignmentName;
    private String dueDate;
    private String grade;
    private String weight;

    public AddAssignmentState() {

    }

    private String getAssignmentName() {return assignmentName;}

    private void setAssignmentName(String assignmentName) {this.assignmentName = assignmentName;}

    private String getDueDate() {return dueDate;}

    private void setDueDate(String dueDate) {this.dueDate = dueDate;}

    private String getGrade() {return grade;}

    private void getGrade(String score) {this.grade = score;}

    private String getWeight() {return weight;}

    private void getWeight(String score) {this.weight = score;}
}
