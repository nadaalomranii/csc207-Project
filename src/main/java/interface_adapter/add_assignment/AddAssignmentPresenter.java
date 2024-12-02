package interface_adapter.add_assignment;

import interface_adapter.ViewManagerModel;
import interface_adapter.assignment_list.AssignmentListState;
import interface_adapter.assignment_list.AssignmentListViewModel;
import use_case.add_assignment.AddAssignmentOutputBoundary;
import use_case.add_assignment.EditAssignmentOutputData;

public class AddAssignmentPresenter implements AddAssignmentOutputBoundary {
    private final AssignmentListViewModel assignmentListViewModel;
    private final ViewManagerModel viewManagerModel;

    public AddAssignmentPresenter(AssignmentListViewModel assignmentListViewModel,
                                  ViewManagerModel viewManagerModel) {
        this.assignmentListViewModel = assignmentListViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(EditAssignmentOutputData outputData) {
        final AssignmentListState assignmentListState = assignmentListViewModel.getState();

        assignmentListState.setCourse(outputData.getCourse());
        assignmentListState.setUser(outputData.getUser());
        // TODO: add the assignment from the output data to the assignment list state

        this.assignmentListViewModel.setState(assignmentListState);
        assignmentListViewModel.firePropertyChanged();
        viewManagerModel.setState(assignmentListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // Assume it never fails
    }
}
