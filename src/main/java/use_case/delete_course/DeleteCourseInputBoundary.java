package use_case.delete_course;

/**
 * The input boundary for the Delete Course Use Case.
 */
public interface DeleteCourseInputBoundary {
    /**
     * Executes the Delete Course Use Case.
     * @param deleteCourseInputData: the input data
     */
    void execute(DeleteCourseInputData deleteCourseInputData);

    /**
     * Executes the switch to course list view.
     */
    void switchToCourseListView();
}
