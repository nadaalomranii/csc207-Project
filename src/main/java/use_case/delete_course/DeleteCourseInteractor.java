package use_case.delete_course;

import data_access.DBUserDataAccessObject;
import data_access.DataAccessInterface;
import entity.Course;
import entity.CourseFactory;

import java.util.ArrayList;
import java.util.List;

public class DeleteCourseInteractor implements DeleteCourseInputBoundary{
    private final DeleteCourseDataAccessInterface dataAccessObject;
    private final DeleteCourseOutputBoundary deleteCoursePresenter;
    private final CourseFactory courseFactory;

    public DeleteCourseInteractor(DeleteCourseDataAccessInterface deleteCourseDataAccessObject,
                                  DeleteCourseOutputBoundary deleteCoursePresenter,
                                  CourseFactory courseFactory) {
        this.dataAccessObject = deleteCourseDataAccessObject;
        this.deleteCoursePresenter = deleteCoursePresenter;
        this.courseFactory = courseFactory;
    }

    @Override
    public void execute(DeleteCourseInputData deleteCourseInputData) {
        final Course course = courseFactory.create(deleteCourseInputData.getCourseName(),
                deleteCourseInputData.getCourseCode(), new ArrayList<>());

        dataAccessObject.deleteCourse(course, deleteCourseInputData.getUser());

        List<Course> currentCourses = dataAccessObject.getCourses(deleteCourseInputData.getUser());

        final DeleteCourseOutputData deleteCourseOutputData = new DeleteCourseOutputData(course.getCode(), deleteCourseInputData.getUser(), currentCourses);

        deleteCoursePresenter.prepareSuccessView(deleteCourseOutputData);
    }

    @Override
    public void switchToCourseListView() {
        deleteCoursePresenter.switchToCourseListView();
    }
}
