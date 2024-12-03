package data_access;

import entity.Assignment;
import entity.CommonAssignment;
import entity.Course;
import entity.User;

import use_case.add_assignment.AddAssignmentDataAccessInterface;
import use_case.add_course.AddCourseDataAccessInterface;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.delete_assignment.DeleteAssignmentDataAccessInterface;
import use_case.delete_course.DeleteCourseDataAccessInterface;
import use_case.edit_assignment.EditAssignmentDataAccessInterface;
import use_case.edit_course.EditCourseDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.send_notification.SendNotificationDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class DataAccessInterface implements
        AddCourseDataAccessInterface,
        EditAssignmentDataAccessInterface,
        DeleteCourseDataAccessInterface,
        EditCourseDataAccessInterface,
        DeleteAssignmentDataAccessInterface,
        SendNotificationDataAccessInterface,
        SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        AddAssignmentDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface {
    // The second key is the course code
    private final Map<User, Map<String, Map<Course, List<Assignment>>>> users = new HashMap<>();

    @Override
    public void saveAssignment(Assignment assignment, Course course, User user) {
        // The course already exists in courses
        // Add the new assignment to the current assignments
        Map<String, Map<Course, List<Assignment>>> courses = this.users.get(user);
        List<Assignment> currentAssignments = courses.get(course.getCode()).get(course);
        if (currentAssignments == null) {
            currentAssignments = new ArrayList<>();
        }
        currentAssignments.add(assignment);
        course.editAssignments(currentAssignments);
        users.get(user).get(course.getCode()).put(course, currentAssignments);
    }

    @Override
    public boolean existsByCode(String code, User user) {
        // checks if the course exists by the course code
        Map<String, Map<Course, List<Assignment>>> courses = this.users.get(user);
        if (courses != null) {
            return courses.containsKey(code);
        }
        // There are no courses under the user, so we return false
        return false;
    }

    @Override
    public void saveCourse(Course course, User user) {
        // Adds the course code as a key
        this.users.get(user).put(course.getCode(), new HashMap<>());
        // Adds the course corresponding to that course code
        this.users.get(user).get(course.getCode()).put(course, new ArrayList<>());
    }

    @Override
    public List<Course> getCourses(User user) {
        // Gets the courses of a specific user
        List<Course> courseObjects = new ArrayList<>();
        Map<String, Map<Course, List<Assignment>>> courses = this.users.get(user);
        if (courses != null) {
            for (Map<Course, List<Assignment>> course : courses.values()) {
                courseObjects.addAll(course.keySet());
            }
        }
        return courseObjects;
    }


    @Override
    public void deleteCourse(Course course, User user) {
        // Deletes the course from the user
        users.get(user).remove(course.getCode());
    }

    @Override
    public void editCourse(Course course, User user) {
        // Edits the course
        Map<String, Map<Course, List<Assignment>>> courses = this.users.get(user);
        Set<Course> currentCourse = courses.get(course.getCode()).keySet();
        // This set only contains one course
        for (Course c : currentCourse) {
            c.changeName(course.getName());
        }
    }

    @Override
    public String checkName(String courseCode, User user) {
        // Returns the name of the course
        Map<String, Map<Course, List<Assignment>>> courses = this.users.get(user);
        Set<Course> currentCourse = courses.get(courseCode).keySet();
        // This set only contains one course
        String courseName = "";
        for (Course c : currentCourse) {
            courseName = c.getName();
        }
        return courseName;
    }


    @Override
    public void deleteAssignment(String assignmentName, Course course, User user) {
        // Deletes the assignment from the course given the assignment name
        Map<String, Map<Course, List<Assignment>>> courses = this.users.get(user);
        List<Assignment> currentAssignments = courses.get(course.getCode()).get(course);
        for (Assignment assignment: currentAssignments){
            if (assignment.getName().equals(assignmentName)){
                this.users.get(user).get(course.getCode()).get(course).remove(assignment);
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
    public MimeMessage draftEmail(Session session, User user, Course course, Assignment assignment) throws MessagingException {
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

        MimeMessage mimeMessage = new MimeMessage(session);
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

    @Override
    public boolean userExistsByName(String username) {
        // checks if the user exists by the name
        boolean exists = false;
        Set<User> allUsers = users.keySet();
        for (User user : allUsers) {
            if (user.getUsername().equals(username)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    @Override
    public void save(User user, List<Course> courses) {
        // saves the user along a list of courses
        Map<String, Map<Course, List<Assignment>>> courseList = new HashMap<>();
        for (Course course : courses) {
            String courseCode = course.getCode();
            Map<Course, List<Assignment>> courseMap = new HashMap<>();
            courseMap.put(course, new ArrayList<>());
            courseList.put(courseCode, courseMap);
        }
        users.put(user, courseList);
    }

    @Override
    public void save(User user) {
        // Saves a user with no courses
        users.put(user, new HashMap<>());
    }

    @Override
    public User get(String username) {
        // Get the user entity of a user with a username
        Set<User> allUsers = this.users.keySet();
        for (User user : allUsers) {
            if (user.getName().equals(username)) {
                return user;
            }
        }
        // We are at the end of the loop and there is no user with this username
        return null;
    }

    @Override
    public String getCurrentUsername() {
        return "";
    }

    @Override
    public void setCurrentUsername(String username) {

    }

    @Override
    public boolean assignmentExistsByName(String name, Course course, User user) {
        // Checks if an assignment exists for a course of a user using the name
        Map<String, Map<Course, List<Assignment>>> courses = this.users.get(user);
        List<Assignment> currentAssignments = courses.get(course.getCode()).get(course);
        for (Assignment assignment : currentAssignments) {
            if (assignment.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean existsByName(String name, Course course, User user) {
        // checks if the assignment exists by name
        Map<String, Map<Course, List<Assignment>>> courses = this.users.get(user);
        List<Assignment> currentAssignments = courses.get(course.getCode()).get(course);
        for (Assignment assignment : currentAssignments) {
            if (assignment.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void editAssignment(Assignment assignment, Course course, User user) {

    }

    @Override
    public void changeScore(Assignment assignment, float newScore, Course course, User user) {
        // change the score of an assignment
        // First, we remove the current assignment
        this.users.get(user).get(course.getCode()).get(course).remove(assignment);
        String assignmentName = assignment.getName();
        float assignmentWeight = assignment.getWeight();
        Date dueDate = assignment.getDueDate();

        // Create an assignment with the new score
        Assignment newAssignment = new CommonAssignment(assignmentName, newScore, assignmentWeight, dueDate);
        // Add that assignment
        this.users.get(user).get(course.getCode()).get(course).add(newAssignment);
    }

    @Override
    public void changeDate(Assignment assignment, Date newDueDate, Course course, User user) {
        // changes the date of an assignment
        // First, we remove the current assignment
        this.users.get(user).get(course.getCode()).get(course).remove(assignment);
        String assignmentName = assignment.getName();
        float assignmentGrade = assignment.getGrade();
        float assignmentWeight = assignment.getWeight();

        // Create an assignment with the new score
        Assignment newAssignment = new CommonAssignment(assignmentName, assignmentGrade, assignmentWeight, newDueDate);
        // Add that assignment
        this.users.get(user).get(course.getCode()).get(course).add(newAssignment);
    }


    @Override
    public void changeWeight(Assignment assignment, float newWeight, Course course, User user) {
        // changes the weight of an assignment
        // First, we remove the current assignment
        this.users.get(user).get(course.getCode()).get(course).remove(assignment);
        String assignmentName = assignment.getName();
        float assignmentGrade = assignment.getGrade();
        Date dueDate = assignment.getDueDate();

        // Create an assignment with the new score
        Assignment newAssignment = new CommonAssignment(assignmentName, assignmentGrade, newWeight, dueDate);
        // Add that assignment
        this.users.get(user).get(course.getCode()).get(course).add(newAssignment);
    }

    @Override
    public void changePassword(User user) {
        // changes the password of a user
        // The user that is passed in contains the changed password
        Map<String, Map<Course, List<Assignment>>> courses = this.users.get(user);
        // Remove the old user
        this.users.remove(user);
        // Add the new users (which has the changed password
        this.users.put(user, courses);
      
    }
}
