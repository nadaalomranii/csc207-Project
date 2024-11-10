package use_case.add_course;

import entity.User;
import entity.UserFactory;

/**
 * The Add Course Interactor.
 */
public class AddCourseInteractor implements AddCourseInputBoundary {

    // declare attributes
    private final AddCourseDataAccessInterface courseDataAccessObject;
    private final AddCourseOutputBoundary courseresenter;
    private final CourseFactory courseFactory;

    // initialise object
    public AddCourseInteractor(AddCourseUserDataAccessInterface addCourseDataAccessInterface,
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
        if (courseDataAccessObject.existsByName(name)) {
            coursePresenter.prepareFailView(name + ": course already exists.");
        }

        // course name does exists
        else {

            // prepare success view
            coursePresenter.prepareSuccessView(name + "added")

            // TODO: add course to courseDataAccessObject
            final Course course = courseDataAccessObject.add()

        }
    }
}
