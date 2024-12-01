package interface_adapter.course_list;

import interface_adapter.ViewModel;

public class CourseListViewModel extends ViewModel<CourseListState> {

    public CourseListViewModel() {
        super("Course List");
        setState(new CourseListState());
    }
}

