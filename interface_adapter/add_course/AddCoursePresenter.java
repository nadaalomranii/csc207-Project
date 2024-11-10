package interface_adapter.add_course;

import interface_adapter.ViewManagerModel;
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
    }

    @Override
    public void prepareFailView(String errorMessage) {

    }

    @Override
    public void switchToAssignmentView() {
        // Assume it never fail
    }
}
