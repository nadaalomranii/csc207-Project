package use_case.add_course;

/**
 * The Add Course Use Case.
 */
public interface AddCourseInputBoundary {

    /**
     * Execute the Add Course Use Case.
     * @param addCourseInputData the input data for this use case
     */
    void execute(AddCourseInputData addCourseInputData);

    /**
     * Switches view to Course List View.
     */
    void switchToCourseView();
}
