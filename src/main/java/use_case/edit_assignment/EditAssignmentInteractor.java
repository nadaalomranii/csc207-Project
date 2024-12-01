package use_case.edit_assignment;

import entity.Assignment;
import entity.AssignmentFactory;


/**
 * The Edit Assignment Interactor.
 */
public class EditAssignmentInteractor implements EditAssignmentInputBoundary {
    private final EditAssignmentDataAccessInterface dataAccess;
    private final EditAssignmentOutputBoundary editAssignmentPresenter;
    private final AssignmentFactory assignmentFactory;

    public EditAssignmentInteractor(EditAssignmentDataAccessInterface assignmentDataAccess,
                                    EditAssignmentOutputBoundary editAssignmentOutputBoundary,
                                    AssignmentFactory assignmentFactory) {
        this.dataAccess = assignmentDataAccess;
        this.editAssignmentPresenter = editAssignmentOutputBoundary;
        this.assignmentFactory = assignmentFactory;
    }

    @Override
    public void execute(EditAssignmentInputData editAssignmentInputData) {
        //execute edit assignment functionality to program
        final Assignment assignment = assignmentFactory.create(editAssignmentInputData.getAssignment().getName(),
                editAssignmentInputData.getNewScore(), editAssignmentInputData.getAssignment().getWeight(),
                 editAssignmentInputData.getAssignment().getDueDate());

        // get assignment name
        final String name = editAssignmentInputData.getName();

        // assignment name already exists; prepare fail view
        if (dataAccess.existsByName(assignment.getName())) {
            editAssignmentPresenter.prepareFailView(name + ": assignment already exists.");
        }

        // assignment name doesn't exist
        else {
        dataAccess.editAssignment(assignment);

        final EditAssignmentOutputData editAssignmentOutputData = new EditAssignmentOutputData(assignment.getGrade()
                , false);

        editAssignmentPresenter.prepareSuccessView(editAssignmentOutputData);
    }
}}
