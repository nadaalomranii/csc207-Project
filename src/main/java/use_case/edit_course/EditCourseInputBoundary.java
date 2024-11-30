package use_case.edit_course;

import use_case.delete_course.DeleteCourseInputData;

/**
 * The Input Boundary for the edit course use case.
 */
public interface EditCourseInputBoundary {
    /**
     * Executes the edit course use case.
     * @param editCourseInputData: the input data
     */
    void execute(EditCourseInputData editCourseInputData);

    /**
     * Executes the switch to course list view.
     */
    void switchToCourseListView();
}
