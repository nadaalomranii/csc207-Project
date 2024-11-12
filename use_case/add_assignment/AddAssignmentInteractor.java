package use_case.add_assignment;

import data_access.DataAccessInterface;
import entity.Assignment;
import entity.AssignmentFactory;
import entity.CommonAssignment;
import entity.CommonAssignmentFactory;

/**
 * The Add Assignment Interactor.
 */
public class AddAssignmentInteractor implements AddAssignmentInputBoundary {
    private final DataAccessInterface courseDataAccessObject;
    private final AddAssignmentOutputBoundary addAssignmentPresenter;

    public AddAssignmentInteractor(DataAccessInterface courseDataAccessInterface,
                                   AddAssignmentOutputBoundary addAssignmentOutputBoundary) {
        this.courseDataAccessObject = courseDataAccessInterface;
        this.addAssignmentPresenter = addAssignmentOutputBoundary;
    }

    @Override
    public void execute(AddAssignmentInputData addAssignmentInputData) {
        final Assignment assignment = CommonAssignmentFactory.create(addAssignmentInputData.getName(),
                addAssignmentInputData.getScore(), addAssignmentInputData.getWeight(), addAssignmentInputData.getDueDate());

        // Save the assignment to the data store
        // TODO temporary fix of casting commonassignment to assignment
        courseDataAccessObject.saveAssignment((CommonAssignment) assignment);

        // Prepare success output and send it to the presenter
        AddAssignmentOutputData outputData = new AddAssignmentOutputData("Assignment added successfully.");
        addAssignmentPresenter.prepareSuccessView(outputData);
    }
}
