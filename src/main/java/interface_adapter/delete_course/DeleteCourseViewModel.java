package interface_adapter.delete_course;

import interface_adapter.ViewModel;

/**
 * The View model for the Delete Course Use Case.
 */
public class DeleteCourseViewModel extends ViewModel<DeleteCourseState> {
    public DeleteCourseViewModel() {
        super("delete course");
        setState(new DeleteCourseState());
    }
}
