package use_case.delete_course;

/**
 * The output boundary for the delete course use case.
 */
public interface DeleteCourseOutputBoundary {
    /**
     * Prepares the Success view for the delete course use case.
     * @param deleteCourseOutputData: the output data
     */
    void prepareSuccessView(DeleteCourseOutputData deleteCourseOutputData);


//    /**
//     * Prepares the fail view for the delete course use case.
//     * @param deleteCourseOutputData: the output data
//     */
//    void prepareFailView(DeleteCourseOutputData deleteCourseOutputData);
    // can never fail

    /**
     * Switches to the course list view.
     */
    void switchToCourseListView();
}
