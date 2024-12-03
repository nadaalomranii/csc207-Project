package interface_adapter.assignment_list;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_assignment.AddAssignmentViewModel;

public class AssignmentListController {

    public void switchToAddAssignmentView(ViewManagerModel viewManagerModel,
                                          AddAssignmentViewModel addAssignmentViewModel,
                                          AssignmentListViewModel assignmentListViewModel) {
        AssignmentListPresenter presenter = new AssignmentListPresenter(viewManagerModel,
                addAssignmentViewModel,
                assignmentListViewModel);
        presenter.switchToAddAssignmentView();
    }

    public void switchToDeleteAssignmentView(){}
}
