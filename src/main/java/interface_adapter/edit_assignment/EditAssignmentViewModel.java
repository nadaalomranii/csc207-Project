package interface_adapter.edit_assignment;

import interface_adapter.ViewModel;

public class EditAssignmentViewModel extends ViewModel<EditAssignmentState> {

    public EditAssignmentViewModel() {
        super("EditAssignment");
        setState(new EditAssignmentState());
    }
}

