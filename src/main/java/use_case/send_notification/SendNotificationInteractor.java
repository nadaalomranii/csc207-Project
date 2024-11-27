package use_case.send_notification;


import entity.Assignment;
import entity.Course;
import entity.User;
import use_case.delete_assignment.DeleteAssignmentOutputData;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

/**
 * The Send Notification Interactor.
 */
public class SendNotificationInteractor implements SendNotificationInputBoundary {

    private final SendNotificationDataAccessInterface dataAccessObject;
    private final SendNotificationOutputBoundary sendNotificationPresenter;

    public SendNotificationInteractor(SendNotificationDataAccessInterface sendNotificationDataAccessInterface,
                                      SendNotificationOutputBoundary sendNotificationOutputBoundary) {
        this.dataAccessObject = sendNotificationDataAccessInterface;
        this.sendNotificationPresenter = sendNotificationOutputBoundary;
    }

    @Override
    public void execute(SendNotificationInputData sendNotificationInputData) throws MessagingException {
        User user = sendNotificationInputData.getUser();
        Assignment assignment = sendNotificationInputData.getAssignment();
        Course course = sendNotificationInputData.getCourse();

        Session session = dataAccessObject.setupServerProperties();
        MimeMessage email = dataAccessObject.draftEmail(session, user, assignment, course);
        dataAccessObject.sendNotification(session, email);

        final SendNotificationOutputData sendNotificationOutputData = new SendNotificationOutputData();
        sendNotificationPresenter.prepareSuccessView(sendNotificationOutputData);
    }
}
//TODO: where is the interactor used?
//TODO: testing
