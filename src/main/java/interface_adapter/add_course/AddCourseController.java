package interface_adapter.add_course;

import use_case.add_course.AddCourseInputBoundary;
import use_case.add_course.AddCourseInputData;

/**
 * Controller for the Add Course Use Case.
 */
public class AddCourseController {

    private final AddCourseInputBoundary addCourseUseCaseInteractor;

    public AddCourseController(AddCourseInputBoundary addCourseUseCaseInteractor) {
        this.addCourseUseCaseInteractor = addCourseUseCaseInteractor;
    }

    /**
     * Executes the Add Course Use Case.
     * @param name The name of the Course
     * @param courseCode The Course code
     */
    public void execute(String name, String courseCode) {
        final AddCourseInputData addCourseInputData = new AddCourseInputData(name, courseCode);
        addCourseUseCaseInteractor.execute(addCourseInputData);
    }

    /**
     * Executes the "switch to AssignmentView" Use Case.
     */
    public void switchToAssignmentView() {addCourseUseCaseInteractor.switchToAssignmentView();}
}
