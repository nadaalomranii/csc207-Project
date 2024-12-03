package interface_adapter.course_list;

import entity.Course;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_course.AddCourseViewModel;
import interface_adapter.assignment_list.AssignmentListState;
import interface_adapter.assignment_list.AssignmentListViewModel;

public class CourseListPresenter {
    private final ViewManagerModel viewManagerModel;
    private final AddCourseViewModel addCourseViewModel;
    private final CourseListViewModel courseListViewModel;
    private final AssignmentListViewModel assignmentListViewModel;

    public CourseListPresenter(ViewManagerModel viewManagerModel,
                               AddCourseViewModel addCourseViewModel,
                               CourseListViewModel courseListViewModel,
                               AssignmentListViewModel assignmentListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.addCourseViewModel = addCourseViewModel;
        this.courseListViewModel = courseListViewModel;
        this.assignmentListViewModel = assignmentListViewModel;
    }

    public void switchToCourseAddView() {
        CourseListState state = courseListViewModel.getState();
        viewManagerModel.setState(addCourseViewModel.getViewName());
        // Set the user for the next state
        addCourseViewModel.getState().setUser(state.getUser());
        viewManagerModel.firePropertyChanged();
    }

    public void switchToAssignmentListView(Course course) {
        CourseListState state = courseListViewModel.getState();
        AssignmentListState newState = new AssignmentListState();
        AssignmentListState assignmentListState = assignmentListViewModel.getState();
        newState.setUser(state.getUser());
        newState.setCourse(course);
        newState.setAssignmentList(course.getAssignments());
        this.assignmentListViewModel.setState(newState);
        assignmentListViewModel.firePropertyChanged();

        viewManagerModel.setState(assignmentListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
