package interface_adapter.add_course;

public class AddCourseViewModel extends ViewModel<AddCourseState> {
    public AddCourseViewModel() {
        super("add course");
        setState(new AddCourseState());
    }
}
