package interface_adapter.delete_assignment;

import interface_adapter.ViewModel;

/**
 * The View model for the Delete Course Use Case.
 */
public class DeleteAssignmentViewModel extends ViewModel<DeleteAssignmentState> {
    // This should be fine
    public DeleteAssignmentViewModel() {
        super("delete assignment");
        setState(new DeleteAssignmentState(getState().getAssignmentName(), getState().getCourse()));
    }
}
