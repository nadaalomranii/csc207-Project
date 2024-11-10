package interface_adapter.add_assignment;

import interface_adapter.ViewManagerModel;
import use_case.add_assignment.AddAssignmentOutputBoundary;
import use_case.add_assignment.AddAssignmentOutputData;

public class AddAssignmentPresenter implements AddAssignmentOutputBoundary {
    private final AssignmentListViewModel assignmentListViewModel;
    private final ViewManagerModel viewManagerModel;

    public AddAssignmentPresenter(AssignmentListViewModel assignmentListViewModel,
                                   ViewManagerModel viewManagerModel) {
        this.assignmentListViewModel = assignmentListViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(AddAssignmentOutputData outputData) {

    }

    @Override
    public void prepareFailView(String errorMessage) {
        // Assume it never fails
    }
}
