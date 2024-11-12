package use_case.add_assignment;

import data_access.DataAccessInterface;
import entity.*;

/**
 * The Add Assignment Interactor.
 */
public class AddAssignmentInteractor implements AddAssignmentInputBoundary {
    private final DataAccessInterface courseDataAccessObject;
    private final AddAssignmentOutputBoundary addAssignmentPresenter;
    private final AssignmentFactory assignmentFactory;

    public AddAssignmentInteractor(DataAccessInterface courseDataAccessInterface,
                                   AddAssignmentOutputBoundary addAssignmentOutputBoundary,
                                   AssignmentFactory assignmentFactory) {
        this.courseDataAccessObject = courseDataAccessInterface;
        this.addAssignmentPresenter = addAssignmentOutputBoundary;
        this.assignmentFactory = assignmentFactory;
    }

    @Override
    public void execute(AddAssignmentInputData addAssignmentInputData) {
        final Assignment assignment = assignmentFactory.create(addAssignmentInputData.getName(),
                addAssignmentInputData.getScore(), addAssignmentInputData.getWeight(), addAssignmentInputData.getDueDate());

        // Save the assignment to the data store
        // Temporary fix of casting commonassignment to assignment FIXED
        courseDataAccessObject.saveAssignment(assignment, addAssignmentInputData.getCourse());

        // Prepare success output and send it to the presenter
        AddAssignmentOutputData outputData = new AddAssignmentOutputData("Assignment added successfully.",
                                                                        addAssignmentInputData.getCourse());
        addAssignmentPresenter.prepareSuccessView(outputData);
    }
}
