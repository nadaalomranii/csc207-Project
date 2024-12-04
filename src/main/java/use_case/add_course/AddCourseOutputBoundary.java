package use_case.add_course;

/**
 * The output boundary for the Add Course Use Case.
 */
public interface AddCourseOutputBoundary {
    /**
     * Prepares the success view for the Add Course Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(AddCourseOutputData outputData);

    /**
     * Prepares the failure view for the Add Course Use Case.
     * @param outputData the output data
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(AddCourseOutputData outputData, String errorMessage);

//    /**
//     * Switches view to Course List View.
//     */
//    void switchToCourseView();

}
