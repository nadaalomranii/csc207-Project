package api;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import entity.Assignment;
import entity.Course;
import entity.User;

public class Mail {
    Session newSession = null;
    MimeMessage mimeMessage = null;


    public Mail(User user, Assignment assignment, Course course) throws MessagingException {
        Mail mail = new Mail(user, assignment, course);
        // TODO: Builder here?
        mail.setupServerProperties();
        mail.draftEmail(user, assignment, course);
        mail.sendEmail();
    }

    private void sendEmail() throws MessagingException {
        String fromEmail = "assignmatenotification@gmail.com";
        String fromPassword = "IAmAWomanInSTEM";
        String emailHost = "smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailHost, fromEmail, fromPassword);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        // TODO: should i add something to indicate to the rest of the code that the email has been sent?
    }

    private MimeMessage draftEmail(User user, Assignment assignment, Course course) throws MessagingException {
        // TODO: remove my email address and use user email address - using mine to make sure algorithm holds.
        // Double check email stuff is fine RE: new InternetAddress(), throws AddressException
        String emailRecipient = user.getEmail();
        //String emailRecipient = "miral.yousef25@gmail.com";
        String emailSubject = String.format("REMINDER: %s (%s) IS COMING UP!", assignment.getName(), course.getCode());
        String emailBody = String.format("Hey %s!%nYou have an assessment coming up and you should lock in.%n%n" +
                        "Course: %s - %s%nAssessment: %s%nDeadline: %s%nWeight: %s%n" +
                        "(you're an academic weapon, go get em queen <3)",
                user.getName(),
                course.getCode(),
                course.getName(),
                assignment.getName(),
                assignment.getDueDate(),
                assignment.getWeight());
        Date emailSendDate = getNotifyDate(assignment);

        MimeMessage mimeMessage = new MimeMessage(newSession);
        mimeMessage.addRecipients(Message.RecipientType.TO, emailRecipient);
        mimeMessage.setSubject(emailSubject);

        MimeMultipart multipart = new MimeMultipart();
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(emailBody, "html/text");
        multipart.addBodyPart(mimeBodyPart);
        mimeMessage.setContent(multipart);
        mimeMessage.setSentDate(emailSendDate);

        return mimeMessage;
    }

    private Date getNotifyDate(Assignment assignment) {

        //constants
        final int TWODAYS = -2;
        final int ONEWEEK = -7;
        final int TWOWEEKS = -14;
        final int THREEWEEKS = -21;

        Date dueDate = assignment.getDueDate();
        Date today = new Date();

        Calendar notifyDate = Calendar.getInstance();
        notifyDate.setTime(dueDate);

        // set calendar to notification date depending on weight
        if (assignment.getWeight() <= 5) {
            notifyDate.add(Calendar.DATE, TWODAYS);
        }
        else if (assignment.getWeight() <= 20) {
            notifyDate.add(Calendar.DATE, ONEWEEK);
        }
        else if (assignment.getWeight() <= 50) {
            notifyDate.add(Calendar.DATE, TWOWEEKS);
        }
        else {
            notifyDate.add(Calendar.DATE, THREEWEEKS);
        }

        // notification time will be at 9am
        notifyDate.set(Calendar.HOUR_OF_DAY, 9);
        notifyDate.set(Calendar.MINUTE, 0);

        // if the notification date is set to a day in the past, it is fixed to today's date, and will send in an hour
        if (notifyDate.getTime().before(today)) {
            notifyDate.setTime(today);
            notifyDate.add(Calendar.HOUR_OF_DAY, 1);
        }

        return notifyDate.getTime();
    }


    private void setupServerProperties() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "547");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        newSession = Session.getDefaultInstance(properties, null);
    }
}
