package use_case.add_assignment;

import entity.Assignment;
import entity.AssignmentFactory;
import entity.Course;
import entity.User;

/**
 * The Add Assignment Interactor.
 */
public class AddAssignmentInteractor implements AddAssignmentInputBoundary {
    private final AddAssignmentDataAccessInterface assignmentDataAccessObject;
    private final AddAssignmentOutputBoundary addAssignmentPresenter;
    private final AssignmentFactory assignmentFactory;

    public AddAssignmentInteractor(AddAssignmentDataAccessInterface addAssignmentDataAccessInterface,
                                   AddAssignmentOutputBoundary addAssignmentOutputBoundary,
                                   AssignmentFactory assignmentFactory) {

        this.assignmentDataAccessObject = addAssignmentDataAccessInterface;
        this.addAssignmentPresenter = addAssignmentOutputBoundary;
        this.assignmentFactory = assignmentFactory;
    }

    @Override
    public void execute(AddAssignmentInputData addAssignmentInputData) {
        final Assignment assignment = assignmentFactory.create(addAssignmentInputData.getName(),
                addAssignmentInputData.getScore(), addAssignmentInputData.getWeight(), addAssignmentInputData.getDueDate());

        // get assignment name
        final String name = assignment.getName();
        final Course course = addAssignmentInputData.getCourse();
        final User user = addAssignmentInputData.getUser();

        // assignment name already exists; prepare fail view
        if (assignmentDataAccessObject.assignmentExistsByName(name, course, user)) {
            addAssignmentPresenter.prepareFailView(name + ": assignment already exists.");
        }

        // assignment name doesn't exist
        else {
        // Save the assignment to the data store
        assignmentDataAccessObject.saveAssignment(assignment, addAssignmentInputData.getCourse(), addAssignmentInputData.getUser());

        // Prepare success output and send it to the presenter
        EditAssignmentOutputData outputData = new EditAssignmentOutputData("Assignment added successfully.",
                                                                        addAssignmentInputData.getCourse(), addAssignmentInputData.getUser());
        addAssignmentPresenter.prepareSuccessView(outputData);
    }
}}
