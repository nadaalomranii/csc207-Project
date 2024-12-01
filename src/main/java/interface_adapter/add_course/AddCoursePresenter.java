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
    private final AddCourseViewModel addCourseViewModel;
    private final CourseListViewModel courseListViewModel;
    private final ViewManagerModel viewManagerModel;

    public AddCoursePresenter(ViewManagerModel viewManagerModel, AddCourseViewModel addCourseViewModel, CourseListViewModel courseListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.addCourseViewModel = addCourseViewModel;
        this.courseListViewModel = courseListViewModel;
    }

    @Override
    public void prepareSuccessView(AddCourseOutputData course) {
        // On success, switch to the Course List View
        final CourseListState courseListState = courseListViewModel.getState();
//        courseListState.getCourse(outputData.getName()); // gives a list of course objects
        // TODO: Need to add the list of courses to the course list state
        // How to do without violating CA?
        this.courseListViewModel.setState(courseListState);
        courseListViewModel.firePropertyChanged();
        viewManagerModel.setState(courseListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    //TODO: implement this
    @Override
    public void prepareFailView(AddCourseOutputData addCourseOutputData, String errorMessage) {
        final AddCourseState addCourseState = addCourseViewModel.getState();
        addCourseState.setAddCourseError(errorMessage);
        addCourseViewModel.firePropertyChanged();
    }

    // TODO: implement this?
    @Override
    public void switchToCourseView() {
        viewManagerModel.setState(courseListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
