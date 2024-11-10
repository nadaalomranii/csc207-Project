package interface_adapter.add_course;

import use_case.add_course.AddCourseOutputBoundary;
import use_case.add_course.AddCourseOutputData;

/**
 * The Presenter for the Signup Use Case.
 */
public class AddCoursePresenter implements AddCourseOutputBoundary {
    private final AddCourseViewModel addCourseViewModel;
    private final ViewManagerModel viewManagerModel;

    public AddCoursePresenter(AddCourseViewModel addCourseViewModel,
                              ViewManagerModel viewManagerModel) {
        this.addCourseViewModel = addCourseViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(AddCourseOutputData outputData) {
        // On sucess, switch to the Course List View
    }

    @Override
    public void prepareFailView(String errorMessage) {

    }

    @Override
    public void switchToAssignmentView() {
        // Assume it never fail
    }
}
