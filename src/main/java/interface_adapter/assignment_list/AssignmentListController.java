package interface_adapter.assignment_list;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_assignment.AddAssignmentViewModel;

import interface_adapter.course_list.CourseListViewModel;
import interface_adapter.edit_course.EditCourseViewModel;

public class AssignmentListController {

    public void switchToAddAssignmentView(ViewManagerModel viewManagerModel,
                                          AddAssignmentViewModel addAssignmentViewModel,
                                          AssignmentListViewModel assignmentListViewModel) {
        AssignmentListPresenter presenter = new AssignmentListPresenter(viewManagerModel,
                addAssignmentViewModel,
                assignmentListViewModel);
        presenter.switchToAddAssignmentView();
    }

    public void switchToEditCourseView(ViewManagerModel viewManagerModel,
                                  EditCourseViewModel editCourseViewModel,
                                  AssignmentListViewModel assignmentListViewModel) {
        AssignmentListPresenter presenter = new AssignmentListPresenter(viewManagerModel, editCourseViewModel, assignmentListViewModel);

        presenter.switchToCourseEditView();
    }

    public void switchToCourseListView(ViewManagerModel viewManagerModel,
                                       CourseListViewModel courseListViewModel,
                                       AssignmentListViewModel assignmentListViewModel) {
        AssignmentListPresenter presenter = new AssignmentListPresenter(viewManagerModel, courseListViewModel, assignmentListViewModel);

        presenter.switchToCourseListView();
    }
}
