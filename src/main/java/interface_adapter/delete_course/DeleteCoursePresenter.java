package interface_adapter.delete_course;

import interface_adapter.ViewManagerModel;
import interface_adapter.course_list.CourseListState;
import interface_adapter.course_list.CourseListViewModel;
import use_case.delete_course.DeleteCourseOutputBoundary;
import use_case.delete_course.DeleteCourseOutputData;

/**
 * The Presenter for the Delete Course Use Case.
 */
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
    public void prepareSuccessView(DeleteCourseOutputData outputData) {
        // On success, switch to the course list view
        CourseListState courseListState = courseListViewModel.getState();
        courseListState.setCourses(outputData.getCourses());
        this.courseListViewModel.setState(courseListState);
        courseListViewModel.firePropertyChanged();
        deleteCourseViewModel.firePropertyChanged("delete course");
        viewManagerModel.setState(courseListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

//    @Override
//    public void prepareFailView(DeleteCourseOutputData deleteCourseOutputData) {
//        // This use case cannot currently fail
//    }
    // Cannot fail

    @Override
    public void switchToCourseListView() {
        viewManagerModel.setState(courseListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
