package interface_adapter.course_list;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_course.AddCourseViewModel;
import interface_adapter.assignment_list.AssignmentListPresenter;
import interface_adapter.assignment_list.AssignmentListViewModel;

public class CourseListController {

    public void switchToCourseAddView(ViewManagerModel viewManagerModel,
                                      AddCourseViewModel addCourseViewModel,
                                      CourseListViewModel courseListViewModel) {
        CourseListPresenter presenter = new CourseListPresenter(viewManagerModel,
                addCourseViewModel,
                courseListViewModel);

        presenter.switchToCourseAddView();
    }

    public void switchToAssignmentListView(ViewManagerModel viewManagerModel,
                                           AssignmentListViewModel assignmentListViewModel,
                                           CourseListViewModel courseListViewModel) {
        AssignmentListPresenter presenter = new AssignmentListPresenter(viewManagerModel, assignmentListViewModel, courseListViewModel);

        presenter.switchToAssignmentListView();
    }
}
