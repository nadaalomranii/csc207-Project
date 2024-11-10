package use_case.add_assignment;

/**
 * The Add Assignment Interactor.
 */
public class AddAssignmentIteractor implements AddAssignmentInputBoundary {
    private AddAssignmentCourseDataAccessInterface courseDataAccessObject;
    private AddAssignmentOutputBoundary addAssignmentPresenter;

    public AddAssignmentIteractor(AddAssignmentCourseDataAccessInterface courseDataAccessInterface,
                                  AddAssignmentOutputBoundary addAssignmentOutputBoundary) {
        this.courseDataAccessObject = courseDataAccessObject;
        this.addAssignmentPresenter = addAssignmentPresenter;
    }

    @Override
    public void execute(AddAssignmentInputData addAssignmentInputData) {
        Assignment assignment = new Assignment(
                addAssignmentInputData.getName(),
                addAssignmentInputData.getDueDate(),
                addAssignmentInputData.getScore(),
                addAssignmentInputData.getWeight()
        );

        // Save the assignment to the data store
        courseDataAccessObject.saveAssignment(assignment);

        // Prepare success output and send it to the presenter
        AddAssignmentOutputData outputData = new AddAssignmentOutputData("Assignment added successfully.");
        addAssignmentPresenter.present(outputData);
    }
}
