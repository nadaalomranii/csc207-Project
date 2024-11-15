package use_case.add_course;

import entity.Course;
import entity.CourseFactory;

/**
 * The Add Course Interactor.
 */
public class AddCourseInteractor implements AddCourseInputBoundary {

    // declare attributes
    private final AddCourseDataAccessInterface courseDataAccessObject;
    private final AddCourseOutputBoundary coursePresenter;
    private final CourseFactory courseFactory;

    // initialise object
    public AddCourseInteractor(AddCourseDataAccessInterface addCourseDataAccessInterface,
                               AddCourseOutputBoundary addCourseOutputBoundary,
                               CourseFactory courseFactory) {

        this.courseDataAccessObject = addCourseDataAccessInterface;
        this.coursePresenter = addCourseOutputBoundary;
        this.courseFactory = courseFactory;
    }

    @Override
    public void execute(AddCourseInputData addCourseInputData) {
        //execute add course functionality to program

        // get course name
        final String name = addCourseInputData.getName();

        // course already exists; prepare fail view

        if (courseDataAccessObject.existsByCode(addCourseInputData.getCode())) {
            coursePresenter.prepareFailView(name + ": course already exists.");
        }

        // course name does exists
        else {
            final Course course = CourseFactory.create(addCourseInputData.getName(), addCourseInputData.getCode());
            final AddCourseOutputData addCourseOutputData = new AddCourseOutputData(course.getCode(), false);
            coursePresenter.prepareSuccessView(addCourseOutputData);
            courseDataAccessObject.save(course);

        }
    }
    @Override
    public void switchToAssignmentView(){
        coursePresenter.switchToAssignmentView();
    }

}
