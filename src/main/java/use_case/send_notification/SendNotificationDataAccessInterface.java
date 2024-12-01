package use_case.send_notification;

import entity.Assignment;
import entity.Course;
import entity.User;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

public interface SendNotificationDataAccessInterface {

    Session setupServerProperties();

    MimeMessage draftEmail(Session session, User user, Course course, Assignment assignment) throws MessagingException;

    void sendNotification(Session session, MimeMessage email) throws MessagingException;

}


