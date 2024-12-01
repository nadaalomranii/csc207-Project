package interface_adapter.edit_assignment;

import interface_adapter.ViewModel;
//  Temporary fix but would be more efficient without the hefty import
public class EditAssignmentViewModel extends ViewModel<src.main.java.interface_adapter.edit_assignment.EditAssignmentState> {
    public EditAssignmentViewModel() {
        super("Edit Assignment");
        setState(new src.main.java.interface_adapter.edit_assignment.EditAssignmentState());
    }
}

