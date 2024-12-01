package interface_adapter.edit_course;

import interface_adapter.ViewManagerModel;
import interface_adapter.assignment_list.AssignmentListState;
import interface_adapter.assignment_list.AssignmentListViewModel;
import use_case.edit_course.EditCourseOutputBoundary;
import use_case.edit_course.EditCourseOutputData;

/**
 * The presenter for the delete course use case.
 */
public class EditCoursePresenter implements EditCourseOutputBoundary {
    private final EditCourseViewModel editCourseViewModel;
    private final AssignmentListViewModel assignmentListViewModel;
    private final ViewManagerModel viewManagerModel;

    public EditCoursePresenter(EditCourseViewModel editCourseViewModel,
                               AssignmentListViewModel assignmentListViewModel,
                               ViewManagerModel viewManager) {
        this.editCourseViewModel = editCourseViewModel;
        this.assignmentListViewModel = assignmentListViewModel;
        this.viewManagerModel = viewManager;
    }

    @Override
    public void prepareSuccessView(EditCourseOutputData editCourseOutputData) {
        // On success, switch back to the assignment list view
        AssignmentListState assignmentListState = assignmentListViewModel.getState();
        this.assignmentListViewModel.setState(assignmentListState);
        assignmentListViewModel.firePropertyChanged();
        editCourseViewModel.firePropertyChanged("edit course");
        viewManagerModel.setState(assignmentListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView() {
        EditCourseState editCourseState = editCourseViewModel.getState();
        editCourseState.setEditError("error");
        editCourseViewModel.firePropertyChanged();
    }

    @Override
    public void switchToAssignmentListView() {
        viewManagerModel.setState(assignmentListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
