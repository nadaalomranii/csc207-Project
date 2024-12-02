package interface_adapter.course_list;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_course.AddCourseViewModel;

public class CourseListPresenter {
    private final ViewManagerModel viewManagerModel;
    private final AddCourseViewModel addCourseViewModel;
    private final CourseListViewModel courseListViewModel;

    public CourseListPresenter(ViewManagerModel viewManagerModel,
                               AddCourseViewModel addCourseViewModel,
                               CourseListViewModel courseListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.addCourseViewModel = addCourseViewModel;
        this.courseListViewModel = courseListViewModel;
    }

    public void switchToCourseAddView() {
        CourseListState state = courseListViewModel.getState();
        viewManagerModel.setState(addCourseViewModel.getViewName());
        // Set the user for the next state
        addCourseViewModel.getState().setUser(state.getUser());
        viewManagerModel.firePropertyChanged();
    }
}
