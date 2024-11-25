package interface_adapter.add_course;

import interface_adapter.ViewModel;

public class AddCourseViewModel extends ViewModel<AddCourseState> {
    public AddCourseViewModel() {
        super("Add Course");
        setState(new AddCourseState());
    }
}
