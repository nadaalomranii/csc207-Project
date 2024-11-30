package interface_adapter.send_notification;

import entity.Course;
import entity.User;
import entity.Assignment;
import use_case.send_notification.SendNotificationInputBoundary;
import use_case.send_notification.SendNotificationInputData;

import javax.mail.MessagingException;

public class SendNotificatonController {

    private final SendNotificationInputBoundary sendNotificationInteractor;

    public SendNotificatonController(SendNotificationInputBoundary sendNotificationInteractor) {
        this.sendNotificationInteractor = sendNotificationInteractor;
    }

    public void execute(User user, Course course, Assignment assignment) throws MessagingException {
        final SendNotificationInputData sendNotificationInputData = new SendNotificationInputData(user, course, assignment);
        sendNotificationInteractor.execute(sendNotificationInputData);
    }
}
