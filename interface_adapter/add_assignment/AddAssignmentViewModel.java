package interface_adapter.add_assignment;

import interface_adapter.ViewModel;

public class AddAssignmentViewModel extends ViewModel<AddAssignmentState> {
    public AddAssignmentViewModel() {
        super("Add Assignment");
        setState(new AddAssignmentState());
    }
}
