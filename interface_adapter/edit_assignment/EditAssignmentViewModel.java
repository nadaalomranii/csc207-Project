package interface_adapter.edit_assignment;

import interface_adapter.ViewModel;
/**
 * The View Model for the Logged In View.
 */
// TODO change according to make it specific to edit assignment
public class EditAssignmentViewModel extends ViewModel<LoggedInState> {

    public EditAssignmentViewModel() {
        super("logged in");
        setState(new LoggedInState());
    }

}
