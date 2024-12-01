package interface_adapter.edit_assignment;

import use_case.edit_assignment.EditAssignmentOutputBoundary;
import use_case.edit_assignment.EditAssignmentOutputData;
import use_case.edit_assignment.EditAssignmentViewModel;

/**
 * The Presenter for the Edit Assignment Use Case.
 */
public class EditAssignmentPresenter implements EditAssignmentOutputBoundary {

    private final EditAssignmentViewModel editAssignmentViewModel;

    public EditAssignmentPresenter(EditAssignmentViewModel editAssignmentViewModel) {
        this.editAssignmentViewModel = editAssignmentViewModel;
    }

    @Override
    public void prepareSuccessView(EditAssignmentOutputData outputData) {
        // currently there isn't anything to change based on the output data,
        // since the output data only contains the new score
        // We still fire the property changed event, but just to let the view know that
        // it can alert the user that their assignment was changed successfully..
        editAssignmentViewModel.firePropertyChanged("Assignment Edited");

    }

    @Override
    public void prepareFailView(String error) {
        // note: this use case currently can't fail
    }
}