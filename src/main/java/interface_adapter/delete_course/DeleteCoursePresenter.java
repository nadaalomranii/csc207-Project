package interface_adapter.delete_course;

import interface_adapter.ViewManagerModel;
import interface_adapter.course_list.CourseListViewModel;
import use_case.delete_course.DeleteCourseOutputBoundary;
import use_case.delete_course.DeleteCourseOutputData;
import view.CourseListView;

public class DeleteCoursePresenter implements DeleteCourseOutputBoundary {
    private final DeleteCourseViewModel deleteCourseViewModel;
    private final CourseListViewModel courseListViewModel;
    private final ViewManagerModel viewManagerModel;

    public DeleteCoursePresenter(DeleteCourseViewModel deleteCourseViewModel,
                                 CourseListViewModel courseListViewModel,
                                 ViewManagerModel viewManagerModel) {
        this.deleteCourseViewModel = deleteCourseViewModel;
        this.courseListViewModel = courseListViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(DeleteCourseOutputData response) {
        // On success, switch to the course list view
        final DeleteCourseState deleteCourseState = deleteCourseViewModel.getState();
        DeleteCourseState.
    }

    @Override
    public void prepareFailView(DeleteCourseOutputData deleteCourseOutputData) {

    }
}
