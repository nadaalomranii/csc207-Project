package use_case.add_course;

import entity.Course;
import entity.CourseFactory;

import java.util.ArrayList;

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
        if (courseDataAccessObject.existsByCode(addCourseInputData.getCode(), addCourseInputData.getUser())) {
            final AddCourseOutputData addCourseOutputData = new AddCourseOutputData();
            coursePresenter.prepareFailView(addCourseOutputData, name + ": course already exists.");
        }

        // course name does not exist; create course, save it and prepare success view
        else {
            final Course course = courseFactory.create(addCourseInputData.getName(), addCourseInputData.getCode(), new ArrayList<>());
            courseDataAccessObject.saveCourse(course, addCourseInputData.getUser());
            final AddCourseOutputData addCourseOutputData = new AddCourseOutputData(course, false);

            coursePresenter.prepareSuccessView(addCourseOutputData);
        }
    }

    @Override
    public void switchToCourseView(){ coursePresenter.switchToCourseView();}

}
