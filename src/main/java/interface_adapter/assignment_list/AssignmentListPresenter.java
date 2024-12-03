package interface_adapter.assignment_list;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_assignment.AddAssignmentViewModel;
import interface_adapter.course_list.CourseListState;
import interface_adapter.course_list.CourseListViewModel;
import view.AssignmentListView;

public class AssignmentListPresenter {
    private final ViewManagerModel viewManagerModel;
    private final AssignmentListViewModel assignmentListViewModel;
    private CourseListViewModel courseListViewModel;
    private AddAssignmentViewModel addAssignmentViewModel;

    public AssignmentListPresenter(ViewManagerModel viewManagerModel,
                                   AssignmentListViewModel assignmentListViewModel,
                                   CourseListViewModel courseListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.assignmentListViewModel = assignmentListViewModel;
        this.courseListViewModel = courseListViewModel;
    }

    public AssignmentListPresenter(ViewManagerModel viewManagerModel,
                                   AddAssignmentViewModel addAssignmentViewModel,
                                   AssignmentListViewModel assignmentListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.addAssignmentViewModel = addAssignmentViewModel;
        this.assignmentListViewModel = assignmentListViewModel;

    }

    //public AssignmentListPresenter(ViewManagerModel, )


    /**
     * Switch from the course list view to the assignment list view for that course.
     */
    public void switchToAssignmentListView() {
        CourseListState state = courseListViewModel.getState();
        viewManagerModel.setState(assignmentListViewModel.getViewName());
        // Set the User for the next state
        assignmentListViewModel.getState().setUser(state.getUser());
        viewManagerModel.firePropertyChanged();
    }

    public void switchToAddAssignmentView() {
        AssignmentListState state = assignmentListViewModel.getState();
        viewManagerModel.setState(addAssignmentViewModel.getViewName());
        //set the user for the next state
        addAssignmentViewModel.getState().setUser(state.getUser());
        viewManagerModel.firePropertyChanged();
    }

    public void switchToDeleteAssignmentView() {
        AssignmentListState state = assignmentListViewModel.getState();
        viewManagerModel.setState(deleteAssignmentViewModel.getViewName());
        //set the user for the next state
    }
}
