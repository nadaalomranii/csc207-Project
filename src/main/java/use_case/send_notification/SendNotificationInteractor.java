package use_case.send_notification;


import entity.Assignment;
import entity.Course;
import entity.User;
import use_case.delete_assignment.DeleteAssignmentOutputData;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;

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
        Course course = sendNotificationInputData.getCourse();
        List<Assignment> assignments = sendNotificationInputData.getAssignments();

        List<Assignment> newlyScheduledAssignments = new ArrayList<>();

        if (assignments != null & !assignments.isEmpty()) {
            for (Assignment assignment : assignments) {
                if (!assignment.isScheduled()) {
                    Session session = dataAccessObject.setupServerProperties();
                    MimeMessage email = dataAccessObject.draftEmail(session, user, course, assignment);
                    dataAccessObject.sendNotification(session, email);
                    assignment.setScheduled(true);
                    newlyScheduledAssignments.add(assignment);
                }
            }
        }

        final SendNotificationOutputData sendNotificationOutputData = new SendNotificationOutputData(newlyScheduledAssignments);
        if (newlyScheduledAssignments.isEmpty()) {
            sendNotificationPresenter.prepareFailView("No new assignments to schedule");
        }
        else {
            sendNotificationPresenter.prepareSuccessView(sendNotificationOutputData);
        }
    }
}

