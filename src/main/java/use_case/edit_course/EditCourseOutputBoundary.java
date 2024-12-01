package use_case.edit_course;

import use_case.delete_course.DeleteCourseOutputData;

/**
 * The output boundary for the edit course use case.
 */
public interface EditCourseOutputBoundary {
    /**
     * Prepares the success view for the edit course use case.
     * @param editCourseOutputData: the output data
     */
    void prepareSuccessView(EditCourseOutputData editCourseOutputData);

    /**
     * Prepares the fail view for the edit course use case.
     * Fails when the user input is empty for the course name and/or the course code.
     */
    void prepareFailView();

    /**
     * Switches to the Assignment list view.
     */
    void switchToAssignmentListView();
}
