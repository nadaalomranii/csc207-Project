package use_case.delete_course;

public interface DeleteCourseInputBoundary {
    /**
     * Executes the Delete Course Use Case.
     * @param deleteCourseInputData: the input data
     */
    void execute(DeleteCourseInputData deleteCourseInputData);
}
