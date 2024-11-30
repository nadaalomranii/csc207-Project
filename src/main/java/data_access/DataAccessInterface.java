package data_access;

import entity.Assignment;
import entity.Course;
import entity.User;

import use_case.add_assignment.AddAssignmentCourseDataAccessInterface;
import use_case.add_course.AddCourseDataAccessInterface;
import use_case.delete_assignment.DeleteAssignmentDataAccessInterface;
import use_case.delete_course.DeleteCourseDataAccessInterface;
import use_case.send_notification.SendNotificationDataAccessInterface;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import java.util.*;

public class DataAccessInterface implements
        AddCourseDataAccessInterface,
        AddAssignmentCourseDataAccessInterface,
        DeleteCourseDataAccessInterface,
        DeleteAssignmentDataAccessInterface,
        SendNotificationDataAccessInterface {

    private final Map<String, Map<Course, List<Assignment>>> courses = new HashMap<>();

    @Override
    public void saveAssignment(Assignment assignment, Course course) {
        // The course already exists in courses
        // Add the new assignment to the current assignments
        List<Assignment> currentAssignments = courses.get(course.getCode()).get(course);
        if (currentAssignments == null) {
            // TODO: Add Assignment as <type>?
            currentAssignments = new ArrayList<>();
        }
        currentAssignments.add(assignment);

        courses.get(course.getCode()).put(course, currentAssignments);
    }

    @Override
    public boolean existsByCode(String code) {
        return courses.containsKey(code);
    }

    @Override
    public void saveCourse(Course course) {courses.put(course.getCode(), new HashMap<>());}


    @Override
    public List<Course> getCourses() {
        List<Course> courseObjects = new ArrayList<>();
        for (Map<Course, List<Assignment>> course : courses.values()) {
            courseObjects.addAll(course.keySet());
        }
        return courseObjects;
    }


    @Override
    public void deleteCourse(Course course) {
        courses.remove(course.getCode());
    }


    @Override
    public void deleteAssignment(String assignmentName, Course course) {
        List<Assignment> currentAssignments = courses.get(course.getCode()).get(course);
        for (Assignment assignment: currentAssignments){
            if (assignment.getName().equals(assignmentName)){
                currentAssignments.remove(assignment);
                break;
            }
        }
    }

    @Override
    public Session setupServerProperties() {
        Session newSession = null;

        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "547");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        newSession = Session.getDefaultInstance(properties, null);

        return newSession;
    }

    @Override
    public MimeMessage draftEmail(Session newSession, User user, Assignment assignment, Course course) throws MessagingException {
        // TODO: remove my email address and use user email address - using mine to make sure algorithm holds.
        // Double check email stuff is fine RE: new InternetAddress(), throws AddressException
        String emailRecipient = user.getEmail();
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

    @Override
    public void sendNotification(Session newSession, MimeMessage mimeMessage) throws MessagingException {
        String fromEmail = "assignmatenotification@gmail.com";
        String fromPassword = "IAmAWomanInSTEM";
        String emailHost = "smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailHost, fromEmail, fromPassword);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
    }
}
