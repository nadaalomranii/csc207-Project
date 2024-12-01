package use_case.send_notification;

/**
 * The output boundary for the Send Notification Use Case.
 */
public interface SendNotificationOutputBoundary {
    /**
     * Prepares the success view for the Send Notification Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(SendNotificationOutputData outputData);

    /**
     * Prepares the failure view for the Send Notification Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    void switchToAddAssignmentView();
}
