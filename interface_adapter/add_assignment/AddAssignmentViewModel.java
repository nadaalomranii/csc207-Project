package interface_adapter.add_assignment;


public class AddAssignmentViewModel extends ViewModel<AddAssignmentState> {
    public AddAssignmentViewModel() {
        super("add assignment");
        setState(new addAssignmentState());
    }
}
