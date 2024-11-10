package interface_adapter.assignment_list;

import interface_adapter.ViewModel;

public class AssignmentListViewModel extends ViewModel<AssignmentListState> {

    public AssignmentListViewModel() {
        super("Assignment List");
        setState(new AssignmentListState());
    }
}
