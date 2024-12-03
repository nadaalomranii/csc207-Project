package use_case.edit_assignment;

import entity.Assignment;

/**
 * The Edit Assignment Interactor.
 */
public class EditAssignmentInteractor implements EditAssignmentInputBoundary {
    private final EditAssignmentDataAccessInterface editAssignmentDataAccessObject;
    private final EditAssignmentOutputBoundary editAssignmentPresenter;

    public EditAssignmentInteractor(EditAssignmentDataAccessInterface editAssignmentDataAccessInterface,
                                    EditAssignmentOutputBoundary editAssignmentOutputBoundary) {
        this.editAssignmentDataAccessObject = editAssignmentDataAccessInterface;
        this.editAssignmentPresenter = editAssignmentOutputBoundary;
    }

    @Override
    public void execute(EditAssignmentInputData editAssignmentInputData) {
        // Get the existing assignment
        Assignment assignment = editAssignmentInputData.getAssignment();

        // Update fields only if the new value is provided
        if (editAssignmentInputData.getNewScore() != 0) {
            editAssignmentDataAccessObject.changeScore(assignment, editAssignmentInputData.getNewScore(), editAssignmentInputData.getCourse(), editAssignmentInputData.getUser());
        }
        if (editAssignmentInputData.getNewDueDate() != null) {
            editAssignmentDataAccessObject.changeDate(assignment, editAssignmentInputData.getNewDueDate(), editAssignmentInputData.getCourse(), editAssignmentInputData.getUser());
        }
        if (editAssignmentInputData.getNewWeight() != 0) {
            editAssignmentDataAccessObject.changeWeight(assignment, editAssignmentInputData.getNewWeight(), editAssignmentInputData.getCourse(), editAssignmentInputData.getUser());
        }
        EditAssignmentOutputData outputData = new EditAssignmentOutputData(editAssignmentInputData.getNewScore(), false);

        editAssignmentPresenter.prepareSuccessView(outputData);
    }
}
