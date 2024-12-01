package interface_adapter.delete_course;

import entity.Course;
import entity.User;
import use_case.delete_course.DeleteCourseInputBoundary;
import use_case.delete_course.DeleteCourseInputData;

/**
 * The Controller for the delete course use case.
 */
public class DeleteCourseController {
    private final DeleteCourseInputBoundary deleteCourseInteractor;

    public DeleteCourseController(DeleteCourseInputBoundary deleteCourseInteractor) {
        this.deleteCourseInteractor = deleteCourseInteractor;
    }

    public void execute(String courseCode, String courseName, User user) {
        final DeleteCourseInputData deleteCourseInputData = new DeleteCourseInputData(courseName, courseCode, user);

        deleteCourseInteractor.execute(deleteCourseInputData);
    }
    public void switchToCourseListView() { deleteCourseInteractor.switchToCourseListView(); }
}
