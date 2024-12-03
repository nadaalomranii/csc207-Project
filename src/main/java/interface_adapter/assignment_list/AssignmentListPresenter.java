package interface_adapter.assignment_list;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_assignment.AddAssignmentViewModel;
import interface_adapter.course_list.CourseListState;
import interface_adapter.course_list.CourseListViewModel;
import interface_adapter.edit_course.EditCourseState;
import interface_adapter.edit_course.EditCourseViewModel;
import view.AssignmentListView;

public class AssignmentListPresenter {
    private final ViewManagerModel viewManagerModel;
    private final AssignmentListViewModel assignmentListViewModel;
    private CourseListViewModel courseListViewModel = null;
    private EditCourseViewModel editCourseViewModel = new EditCourseViewModel();
    private AddAssignmentViewModel addAssignmentViewModel;

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
                                   AddAssignmentViewModel addAssignmentViewModel,
                                   AssignmentListViewModel assignmentListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.addAssignmentViewModel = addAssignmentViewModel;
        this.assignmentListViewModel = assignmentListViewModel;

    }

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

    public void switchToAddAssignmentView() {
        AssignmentListState state = assignmentListViewModel.getState();
        viewManagerModel.setState(addAssignmentViewModel.getViewName());
        //set the user for the next state
        addAssignmentViewModel.getState().setUser(state.getUser());
        viewManagerModel.firePropertyChanged();
    }

}
