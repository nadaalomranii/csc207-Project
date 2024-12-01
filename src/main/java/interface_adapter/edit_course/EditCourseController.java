package interface_adapter.edit_course;

import entity.User;
import use_case.edit_course.EditCourseInputBoundary;
import use_case.edit_course.EditCourseInputData;
import use_case.edit_course.EditCourseOutputBoundary;

/**
 * The controller for the edit course use case
 */
public class EditCourseController {
    private final EditCourseInputBoundary editCourseInteractor;

    public EditCourseController(EditCourseInputBoundary editCourseInteractor) {
        this.editCourseInteractor = editCourseInteractor;
    }

    public void execute(String courseCode, String courseName, User user) {
        final EditCourseInputData editCourseInputData = new EditCourseInputData(courseCode, courseName, user);

        editCourseInteractor.execute(editCourseInputData);
    }

    public void switchToAssignmentListView() {
        editCourseInteractor.switchToAssignmentListView();
    }
}
