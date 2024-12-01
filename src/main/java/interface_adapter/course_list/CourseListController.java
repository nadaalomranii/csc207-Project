package interface_adapter.course_list;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_course.AddCourseViewModel;

public class CourseListController {

    public void switchToCourseListView(ViewManagerModel viewManagerModel,
                                       AddCourseViewModel addCourseViewModel,
                                       CourseListViewModel courseListViewModel) {
        CourseListPresenter presenter = new CourseListPresenter(viewManagerModel,
                addCourseViewModel,
                courseListViewModel);

        presenter.switchToCourseListView();
    }
}
