package interface_adapter.delete_assignment;

import interface_adapter.ViewManagerModel;
import interface_adapter.assignment_list.AssignmentListState;
import interface_adapter.assignment_list.AssignmentListViewModel;
import use_case.delete_assignment.DeleteAssignmentOutputBoundary;
import use_case.delete_assignment.DeleteAssignmentOutputData;

/**
 * The Presenter for the Delete Assignment Use Case.
 */
public class DeleteAssignmentPresenter implements DeleteAssignmentOutputBoundary {
    // This should be fine

    private final AssignmentListViewModel assignmentListViewModel;
    private final ViewManagerModel viewManagerModel;

    public DeleteAssignmentPresenter(AssignmentListViewModel assignmentListViewModel,
                                     ViewManagerModel viewManagerModel) {
        this.assignmentListViewModel = assignmentListViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(DeleteAssignmentOutputData outputData) {
        // On success, switch to the assignment list view
        AssignmentListState assignmentListState = assignmentListViewModel.getState();

        this.assignmentListViewModel.setState(assignmentListState);
        assignmentListViewModel.firePropertyChanged("delete assignment");
        viewManagerModel.setState(assignmentListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }


    @Override
    public void prepareFailView(DeleteAssignmentOutputData deleteAssignmentOutputData) {
        // This use case cannot currently fail
        // TODO: if it's by click, does this mean that the use case can't fail, cuz the click automatically makes the input data valid?
        // TODO: is it going to be by click, or will the user enter the name of the thing?
    }


//    @Override
//    public void switchToAssignmentView() {
//        viewManagerModel.setState(assignmentListViewModel.getViewName());
//        viewManagerModel.firePropertyChanged();
    //}
}
//TODO: where is this used?