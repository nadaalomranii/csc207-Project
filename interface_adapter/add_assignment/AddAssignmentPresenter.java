package interface_adapter.add_assignment;

import use_case.add_assignment.AddAssignmentOutputBoundary;
import use_case.add_assignment.AddAssignmentOutputData;

public class AddAssignmentPresenter implements AddAssignmentOutputBoundary {
    private final AddAssignmentViewModel addAssignmentViewModel;
    private final ViewManagerModel viewManagerModel;

    public AddAssignmentPresenter(AddAssignmentViewModel addAssignmentViewModel,
                                   ViewManagerModel viewManagerModel) {
        this.addAssignmentViewModel = addAssignmentViewModel;
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
