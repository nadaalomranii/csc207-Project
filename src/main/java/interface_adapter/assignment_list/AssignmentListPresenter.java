package interface_adapter.assignment_list;

import interface_adapter.ViewManagerModel;
import interface_adapter.course_list.CourseListState;
import interface_adapter.course_list.CourseListViewModel;
import interface_adapter.edit_course.EditCourseState;
import interface_adapter.edit_course.EditCourseViewModel;
import view.AssignmentListView;

public class AssignmentListPresenter {
    private final ViewManagerModel viewManagerModel;
    private final AssignmentListViewModel assignmentListViewModel;
    private CourseListViewModel courseListViewModel = new CourseListViewModel();
    private EditCourseViewModel editCourseViewModel = new EditCourseViewModel();

    public AssignmentListPresenter(ViewManagerModel viewManagerModel,
                                   AssignmentListViewModel assignmentListViewModel,
                                   CourseListViewModel courseListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.assignmentListViewModel = assignmentListViewModel;
        this.courseListViewModel = courseListViewModel;
    }

    public AssignmentListPresenter(ViewManagerModel viewManagerModel,
                                   EditCourseViewModel editCourseViewModel,
                                   AssignmentListViewModel assignmentListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.assignmentListViewModel = assignmentListViewModel;
        this.editCourseViewModel = editCourseViewModel;
    }

    public AssignmentListPresenter(ViewManagerModel viewManagerModel,
                                   CourseListViewModel courseListViewModel,
                                   AssignmentListViewModel assignmentListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.assignmentListViewModel = assignmentListViewModel;
        this.courseListViewModel = courseListViewModel;
    }

    /**
     * Switches from the assignment list view to the course edit view
     */
    public void switchToCourseEditView() {
        AssignmentListState state = assignmentListViewModel.getState();
        EditCourseState editCourseState = new EditCourseState();

        // Set the values for the next state
        editCourseState.setUser(state.getUser());
        editCourseState.setCourseName(state.getCourse().getName());
        editCourseState.setCourseCode(state.getCourse().getCode());

        editCourseViewModel.setState(editCourseState);
        // Set the next state
        viewManagerModel.setState(editCourseViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    /**
     * Switches from the assignment list view to the course list view
     */
    public void switchToCourseListView() {
        AssignmentListState state = assignmentListViewModel.getState();
        CourseListState courseState = new CourseListState();

        // Set the values for the next state
        courseState.setUser(state.getUser());

        courseListViewModel.setState(courseState);

        // Set the next state
        viewManagerModel.setState(courseListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Switch from the course list view to the assignment list view for that course.
     */
    public void switchToAssignmentListView() {
        CourseListState state = courseListViewModel.getState();

        // Set the User for the next state
        this.assignmentListViewModel.getState().setUser(state.getUser());
        assignmentListViewModel.firePropertyChanged();

        viewManagerModel.setState(assignmentListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
