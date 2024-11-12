package interface_adapter.assignment_list;

import entity.Assignment;
import entity.Course;

import java.util.List;

public class AssignmentListState {
    private List<Assignment> assignmentList;
    private Course course;

    public AssignmentListState() {

    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
