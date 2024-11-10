package interface_adapter.add_course;

import interface_adapter.ViewManagerModel;
import interface_adapter.course_list.CourseListState;
import interface_adapter.course_list.CourseListViewModel;
import use_case.add_course.AddCourseOutputBoundary;
import use_case.add_course.AddCourseOutputData;

/**
 * The Presenter for the Signup Use Case.
 */
public class AddCoursePresenter implements AddCourseOutputBoundary {
    private final CourseListViewModel courseListViewModel;
    private final ViewManagerModel viewManagerModel;

    public AddCoursePresenter(ViewManagerModel viewManagerModel, CourseListViewModel courseListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.courseListViewModel = courseListViewModel;
    }

    @Override
    public void prepareSuccessView(AddCourseOutputData outputData) {
        // On success, switch to the Course List View
        final CourseListState courseListState = courseListViewModel.getState();

        // TODO: Need to add the course to the course state

        this.courseListViewModel.setState(courseListState);
        courseListViewModel.firePropertyChanged();
        viewManagerModel.setState(courseListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // Assume that it never fails
    }

    @Override
    public void switchToAssignmentView() {

    }
}
