package use_case.delete_assignment;

import entity.Assignment;

public class DeleteAssignmentOutputData {
    private final String assignmentName;

    public DeleteAssignmentOutputData(String assignmentName) {this.assignmentName = assignmentName;}

    public String getAssignmentName() { return assignmentName; }
}

