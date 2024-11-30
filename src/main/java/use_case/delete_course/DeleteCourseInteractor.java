package use_case.delete_course;

import entity.Course;
import entity.CourseFactory;

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
                deleteCourseInputData.getCourseCode());

        dataAccessObject.deleteCourse(course);

        final DeleteCourseOutputData deleteCourseOutputData = new DeleteCourseOutputData(course.getCode());

        deleteCoursePresenter.prepareSuccessView(deleteCourseOutputData);
    }

    @Override
    public void switchToCourseListView() {
        deleteCoursePresenter.switchToCourseListView();
    }
}