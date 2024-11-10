package use_case.add_assignment;

import data_access.DataAccessInterface;

/**
 * The Add Assignment Interactor.
 */
public class AddAssignmentInteractor implements AddAssignmentInputBoundary {
    private DataAccessInterface courseDataAccessObject;
    private AddAssignmentOutputBoundary addAssignmentPresenter;

    public AddAssignmentInteractor(DataAccessInterface courseDataAccessInterface,
                                   AddAssignmentOutputBoundary addAssignmentOutputBoundary) {
        this.courseDataAccessObject = courseDataAccessInterface;
        this.addAssignmentPresenter = addAssignmentOutputBoundary;
    }

    @Override
    public void execute(AddAssignmentInputData addAssignmentInputData) {
        Assignment assignment = new Assignment(
                addAssignmentInputData.getName(),
                addAssignmentInputData.getDueDate(),
                addAssignmentInputData.getScore(),
                addAssignmentInputData.getWeight(),
                addAssignmentInputData.getCourseCode()
        );

        // Save the assignment to the data store
        courseDataAccessObject.saveAssignment(assignment);

        // Prepare success output and send it to the presenter
        AddAssignmentOutputData outputData = new AddAssignmentOutputData("Assignment added successfully.");
        addAssignmentPresenter.present(outputData);
    }
}
