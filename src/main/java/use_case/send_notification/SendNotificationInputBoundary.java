package use_case.send_notification;

/**
 * The Send Notification Use Case.
 */
public interface SendNotificationInputBoundary {
    /**
     * Execute the Send Notification Use Case.
     * @param sendNotificationInputData the input data for this use case
     */
    void execute(SendNotificationInputData sendNotificationInputData);
}
