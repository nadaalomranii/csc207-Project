package use_case.delete_assignment;

import entity.Assignment;
import entity.Course;

public class DeleteAssignmentInteractor implements DeleteAssignmentInputBoundary {
    private final DeleteAssignmentDataAccessInterface dataAccessObject;
    private final DeleteAssignmentOutputBoundary deleteAssignmentPresenter;

    public DeleteAssignmentInteractor(DeleteAssignmentDataAccessInterface deleteAssignmentDataAccessObject,
                                      DeleteAssignmentOutputBoundary deleteAssignmentPresenter) {
        this.dataAccessObject = deleteAssignmentDataAccessObject;
        this.deleteAssignmentPresenter = deleteAssignmentPresenter;
    }

    @Override
    public void execute(DeleteAssignmentInputData deleteAssignmentInputData) {
        Course course = deleteAssignmentInputData.getCourse();
        String assignmentName = deleteAssignmentInputData.getAssignmentName();
        dataAccessObject.deleteAssignment(assignmentName, course, deleteAssignmentInputData.getUser());


        final DeleteAssignmentOutputData deleteAssignmentOutputData = new DeleteAssignmentOutputData(assignmentName);
        deleteAssignmentPresenter.prepareSuccessView(deleteAssignmentOutputData);
    }

    @Override
    public void switchToAssignmentView() {
        deleteAssignmentPresenter.switchToAssignmentView();
    }
}
