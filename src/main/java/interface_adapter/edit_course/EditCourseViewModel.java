package interface_adapter.edit_course;

import interface_adapter.ViewModel;

public class EditCourseViewModel extends ViewModel<EditCourseState> {
    public EditCourseViewModel() {
        super("edit course");
        setState(new EditCourseState());
    }
}
