package use_case.edit_course;

import entity.Course;
import entity.CourseFactory;

public class EditCourseInteractor implements EditCourseInputBoundary {
    private final EditCourseDataAccessInterface dataAccessObject;
    private final EditCourseOutputBoundary editCoursePresenter;
    private final CourseFactory courseFactory;

    public EditCourseInteractor(EditCourseDataAccessInterface dataAccessObject,
                                EditCourseOutputBoundary editCoursePresenter,
                                CourseFactory courseFactory) {
        this.dataAccessObject = dataAccessObject;
        this.editCoursePresenter = editCoursePresenter;
        this.courseFactory = courseFactory;
    }

    @Override
    public void execute(EditCourseInputData editCourseInputData) {
        final Course course = courseFactory.create(editCourseInputData.getCourseName(),
                editCourseInputData.getCourseCode());

        dataAccessObject.editCourse(course);

        final EditCourseOutputData editCourseOutputData = new EditCourseOutputData(course.getName(), course.getCode());

        editCoursePresenter.prepareSuccessView(editCourseOutputData);
        // Check the fail view
    }

    @Override
    public void switchToAssignmentListView() {
        editCoursePresenter.switchToAssignmentListView();
    }
}
