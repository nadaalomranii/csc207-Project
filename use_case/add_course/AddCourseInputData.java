package use_case.add_course;

/**
 * The input data for the Add Course Use Case.
 */
public class AddCourseInputData {

    private final String name;
    private final List<Assignment> assignments;

    public AddCourseInputData(String name, List<Assignment> assignments) {
        this.name = name;
        this.assignments = assignments;
    }

    String getName() {
        return name;
    }

    List<Assignments> getAssignments() {
        return assignments;
    }

}
