package interface_adapter.course_list;

import entity.Course;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_course.AddCourseViewModel;
import interface_adapter.assignment_list.AssignmentListPresenter;
import interface_adapter.assignment_list.AssignmentListViewModel;
import view.CourseListView;

public class CourseListController {

    public void switchToCourseAddView(ViewManagerModel viewManagerModel,
                                      AddCourseViewModel addCourseViewModel,
                                      CourseListViewModel courseListViewModel,
                                      AssignmentListViewModel assignmentListViewModel) {
        CourseListPresenter presenter = new CourseListPresenter(viewManagerModel,
                addCourseViewModel,
                courseListViewModel, assignmentListViewModel);

        presenter.switchToCourseAddView();
    }

    public void switchToAssignmentListView(ViewManagerModel viewManagerModel,
                                           AddCourseViewModel addCourseViewModel,
                                           AssignmentListViewModel assignmentListViewModel,
                                           CourseListViewModel courseListViewModel, Course course) {
        CourseListPresenter presenter = new CourseListPresenter(viewManagerModel,
                addCourseViewModel,
                courseListViewModel,
                assignmentListViewModel);

        presenter.switchToAssignmentListView(course);
    }
}
