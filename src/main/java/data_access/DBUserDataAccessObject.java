package data_access;

import entity.*;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The DAO for user data.
 */
public class DBUserDataAccessObject implements
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
    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String INFO = "info";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String MESSAGE = "message";
    private final UserFactory userFactory;
    private final CourseFactory courseFactory;
    private final AssignmentFactory assignmentFactory;

    public DBUserDataAccessObject(UserFactory userFactory,
                                  CourseFactory courseFactory,
                                  AssignmentFactory assignmentFactory) {
        this.userFactory = userFactory;
        this.courseFactory = courseFactory;
        this.assignmentFactory = assignmentFactory;
    }

    /**
     * Saves the user. This is intended for when the user is logging in.
     * @param user the user to save
     */
    @Override
    public void save(User user) {
        save(user, new ArrayList<>());
    }

    /// /// /// /// ///

    /**
     * Saves the user. This is intended for when the user is first signing up.
     * @param user the user to save
     * @param courses the courses to be saved with this user, should there be any
     */
    @Override
    public void save(User user, List<Course> courses) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // POST METHOD
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME, user.getUsername());
        requestBody.put(PASSWORD, user.getPassword());
        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/user")
                .method("POST", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // success!
                updateUserInfo(user, courses);
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    /// /// /// /// ///

    public void updateUserInfo(User user, List<Course> courses) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // POST METHOD
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME, user.getUsername());
        requestBody.put(PASSWORD, user.getPassword());

        // Create the user's courses information
        final JSONObject userInfo = new JSONObject();
        userInfo.put(NAME, user.getName());
        userInfo.put(EMAIL, user.getEmail());

        for (Course course : courses) {
            userInfo.put(course.getCode(), generateCourseInfo(course));
        }

        requestBody.put(INFO, userInfo);
        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/modifyUserInfo")
                .method("PUT", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // success!
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    private JSONObject generateCourseInfo(Course course) {
        final JSONObject courseInfo = new JSONObject();
        courseInfo.put("course-name", course.getName());
        JSONArray assignments = new JSONArray();

        for (Assignment assignment : course.getAssignments()) {
            JSONObject assignmentInfo = new JSONObject();
            assignmentInfo.put("assignment-name", assignment.getName());
            assignmentInfo.put("grade", assignment.getGrade());
            assignmentInfo.put("weight", assignment.getWeight());

            // Convert due date into String for proper storing
            String dateFormatPattern = "dd-MM-yyyy";
            DateFormat df = new SimpleDateFormat(dateFormatPattern);
            String dueDateString = df.format(assignment.getDueDate());
            assignmentInfo.put("due-date", dueDateString);

            assignments.put(assignmentInfo);
        }

        return courseInfo;
    }

    /// /// /// /// ///

    /**
     * Get the user based on the given username.
     * @param username the user's associated username
     * @return the user to be recieved
     */
    @Override
    public User get(String username) {
        // Make an API call to get the user object.
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", username))
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                final JSONObject userJSONObject = responseBody.getJSONObject("user");
                final String password = userJSONObject.getString(PASSWORD);
                final JSONObject information = userJSONObject.getJSONObject(INFO);
                final String name = information.getString(NAME);
                final String email = information.getString(EMAIL);
                return userFactory.create(username, password, name, email);
            } else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        } catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    /// /// /// /// ///

    /**
     * Get the user's courses based on the given username.
     * @param username the user's associated username
     * @return a list of user's courses with their assignments
     */
    public List<Course> getCourses(String username) {
        // Make an API call to get the user object.
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", username))
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // The list where the courses will be stored
                final List<Course> courseList = new ArrayList<>();

                // Accessing "info" from the database
                final JSONObject userJSONObject = responseBody.getJSONObject("user");
                final JSONObject information = userJSONObject.getJSONObject(INFO);

                // Determine the items inside info
                Iterator<String> items = information.keys();
                // Avoid "name" and "email," we only want the courses
                List<String> avoidedItems = Arrays.asList("name", "email");
                String courseCode = items.next();
                while (items.hasNext()) {
                    if (!avoidedItems.contains(courseCode)) {
                        // Get the course JSON object for this course code
                        JSONObject courseJSONObject = information.getJSONObject(courseCode);

                        // Get the name of the course (for course entity)
                        String courseName = courseJSONObject.getString("course-name");

                        // Create the list to store this course's assignments
                        JSONArray assignments = courseJSONObject.getJSONArray("assignments");
                        List<Assignment> assignmentList = getCourseAssignments(assignments);

                        courseList.add(courseFactory.create(courseName, courseCode, assignmentList));
                    }
                    courseCode = items.next();
                }

                return courseList;

            } else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        } catch (IOException | JSONException | ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    private List<Assignment> getCourseAssignments(JSONArray assignments) throws ParseException {
        List<Assignment> courseAssignments = new ArrayList<>();
        for (int i = 0; i < assignments.length(); i++) {

            JSONObject assignmentJSONObject = assignments.getJSONObject(i);

            String assignmentName = assignmentJSONObject.getString("assignment-name");
            float grade = assignmentJSONObject.getFloat("grade");
            float weight = assignmentJSONObject.getFloat("weight");
            // Format due date from String to Date
            String dueDateString = assignmentJSONObject.getString("due-date");
            Date dueDate = new SimpleDateFormat("dd-MM-yyyy").parse(dueDateString);

            courseAssignments.add(assignmentFactory.create(assignmentName, grade, weight, dueDate));
        }
        return courseAssignments;
    }

    /// /// /// /// ///

    @Override
    public void setCurrentUsername(String name) {
        // Action for user to set their username is not intended.
    }

    /// /// /// /// ///

    /**
     * Checks if the given username exists.
     *
     * @param username the username to look for
     * @return true if a user with the given username exists; false otherwise
     */
    @Override
    public boolean userExistsByName(String username) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/checkIfUserExists?username=%s", username))
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            return responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE;
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    /// /// /// /// ///

    public void changePassword(User user) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // POST METHOD
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME, user.getUsername());
        requestBody.put(PASSWORD, user.getPassword());
        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/user")
                .method("PUT", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // success!
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    /// /// /// /// ///

    @Override
    public String getCurrentUsername() {
        return null;
    }

    /// /// /// /// ///

    /**
     * Checks if the assignment already exists.
     * @param name   the assignment name to look for
     * @param course the course in which to check for the assignment
     * @param user   the user associated with the course and assignment
     * @return true if a course with the given code exists; false otherwise
     */
    @Override
    public boolean assignmentExistsByName(String name, Course course, User user) {
        List<Course> courses = getCourses(user);
        // Assuming the course for this assignment exists
        int courseIndex = courses.indexOf(course);
        Course courseWithAssignment = courses.get(courseIndex);
        List<Assignment> assignmentList = courseWithAssignment.getAssignments();
        for (Assignment a : assignmentList) {
            if (a.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /// /// /// /// ///

    /**
     * Add an assignment to a course.
     * @param assignment the assignment to be added
     * @param course the course the assignment is stored in
     * @param user the user with this assignment
     */
    @Override
    public void saveAssignment(Assignment assignment, Course course, User user) {
        List<Course> courses = getCourses(user);
        Course courseWithAssignment = courses.get(courses.indexOf(course));
        courseWithAssignment.getAssignments().add(assignment);
        updateUserInfo(user, courses);
    }

    /// /// /// /// ///

    /**
     * Checks if the course already exists.
     * @param code the course code to look for
     * @param user the user with courses
     * @return true if a course with the given code exists; false otherwise
     */
    @Override
    public boolean existsByCode(String code, User user) {
        List<Course> courses = getCourses(user);
        for (Course course : courses) {
            if (course.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    /// /// /// /// ///

    /**
     * Saves the course.
     * @param course the course to save
     * @param user the user who has this course
     */
    @Override
    public void saveCourse(Course course, User user) {
        List<Course> courses = getCourses(user);
        courses.add(course);
        updateUserInfo(user, courses);
    }

    /// /// /// /// ///

    /**
     * Get the courses this user has.
     * @param user the user with courses, if any
     * @return the list of courses this user has
     */
    @Override
    public List<Course> getCourses(User user) {
        return getCourses(user.getUsername());
    }

    /// /// /// /// ///

    /**
     * Remove this assignment from the user's course.
     * @param assignmentName the name of the assignment to be deleted
     * @param course the course this assignment is located in
     * @param user the user who had this assignment
     */
    @Override
    public void deleteAssignment(String assignmentName, Course course, User user) {
        List<Course> courses = getCourses(user);
        int courseIndex = courses.indexOf(course);
        Course courseWithAssignment = courses.get(courseIndex);
        List<Assignment> assignmentList = courseWithAssignment.getAssignments();
        for (Assignment a : assignmentList) {
            if (a.getName().equals(assignmentName)) {
                courseWithAssignment.getAssignments().remove(a);
                updateUserInfo(user, courses);
            }
        }
    }

    /// /// /// /// ///

    /**
     * Delete this course from the user's courses.
     * @param course the course to be removed
     * @param user the user who had the deleted course
     */
    @Override
    public void deleteCourse(Course course, User user) {
        List<Course> courses = getCourses(user);
        courses.remove(course);
        updateUserInfo(user, courses);
    }

    /// /// /// /// ///
    /**
     * Checks if the assignment already exists.
     * @param name the assignment name to look for
     * @param course the course the assignment should be located in
     * @param user the user with this assignment
     * @return true if a course with the given code exists; false otherwise
     */
    @Override
    public boolean existsByName(String name, Course course, User user) {
        List<Course> courses = getCourses(user);
        int courseIndex = courses.indexOf(course);
        Course courseWithAssignment = courses.get(courseIndex);
        List<Assignment> assignmentList = courseWithAssignment.getAssignments();
        for (Assignment assignment : assignmentList) {
            if (assignment.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /// /// /// /// ///

    /**
     * Updates the system to record this assignment's updates.
     * @param assignment the assignment to be updated
     * @param course the course this assignment is located in
     * @param user the user with this assignment
     */
    @Override
    public void editAssignment(Assignment assignment, Course course, User user) {
        changeScore(assignment, assignment.getGrade(), course, user);
        changeDate(assignment, assignment.getDueDate(), course, user);
        changeWeight(assignment, assignment.getWeight(), course, user);
    }

    /**
     * Update the grade of this assignment
     * @param assignment the assignment to be edited
     * @param newScore the new grade for this assignment
     * @param course the course this assignment is located in
     * @param user the user with this assignment
     */
    @Override
    public void changeScore(Assignment assignment, float newScore, Course course, User user) {
        List<Course> courses = getCourses(user);
        int courseIndex = courses.indexOf(course);
        Course courseWithAssignment = courses.get(courseIndex);
        List<Assignment> assignmentList = courseWithAssignment.getAssignments();
        for (Assignment a : assignmentList) {
            if (a.getName().equals(assignment.getName())) {
                a.changeGrade(newScore);
                updateUserInfo(user, courses);
            }
        }
    }

    /**
     * Update the due date of this assignment
     * @param assignment the assignment to be edited
     * @param newDueDate the new due date for this assignment
     * @param course the course this assignment is located in
     * @param user the user with this assignment
     */
    @Override
    public void changeDate(Assignment assignment, Date newDueDate, Course course, User user) {
        List<Course> courses = getCourses(user);
        int courseIndex = courses.indexOf(course);
        Course courseWithAssignment = courses.get(courseIndex);
        List<Assignment> assignmentList = courseWithAssignment.getAssignments();
        for (Assignment a : assignmentList) {
            if (a.getName().equals(assignment.getName())) {
                a.changeDueDate(newDueDate);
                updateUserInfo(user, courses);
            }
        }
    }

    /**
     * Update the weight of this assignment
     * @param assignment the assignment to be edited
     * @param newWeight the new weight for this assignment
     * @param course the course this assignment is located in
     * @param user the user with this assignment
     */
    @Override
    public void changeWeight(Assignment assignment, float newWeight, Course course, User user) {
        List<Course> courses = getCourses(user);
        int courseIndex = courses.indexOf(course);
        Course courseWithAssignment = courses.get(courseIndex);
        List<Assignment> assignmentList = courseWithAssignment.getAssignments();
        for (Assignment a : assignmentList) {
            if (a.getName().equals(assignment.getName())) {
                a.changeWeight(newWeight);
                updateUserInfo(user, courses);
            }
        }
    }

    /// /// /// /// ///

    /**
     * Update the name of the given course.
     * @param course the course with the name change
     * @param user the user who has this course
     */
    @Override
    public void editCourse(Course course, User user) {
        List<Course> courses = getCourses(user);
        for (Course c : courses) {
            if (c.getCode().equals(course.getCode())) {
                c.changeName(course.getName());
                updateUserInfo(user, courses);
            }
        }
    }

    /// /// /// /// ///

    /**
     * Return the name of the course with this course code for this user.
     * @param courseCode the course code associated with the course
     * @param user the user who has this course
     * @return the course name, or null if a course with that course code is not present.
     */
    @Override
    public String checkName(String courseCode, User user) {
        List<Course> courses = getCourses(user);
        for (Course course : courses) {
            if (course.getCode().equals(courseCode)) {
                return course.getName();
            }
        }
        return null;
    }

    /// /// /// /// ///

    /**
     * Set up the server to send emails and notifications.
     * @return the server session.
     */
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

    /// /// /// /// ///

    /**
     * Draft the email to notify the user of an upcoming assignment.
     * @param session the server session
     * @param user the user to send the email to
     * @param course the course to email about
     * @param assignment the assignment to email out
     * @return the message that will be sent
     * @throws MessagingException
     */
    @Override
    public MimeMessage draftEmail(Session session, User user, Course course, Assignment assignment) throws MessagingException {
        String emailRecipient = user.getEmail();
        String emailSubject = String.format("REMINDER: %s (%s) IS COMING UP!", assignment.getName(), course.getCode());
        String emailBody = String.format("Hey %s!%nYou have an assessment coming up and you should lock in.%n%n" +
                        "Course: %s - %s%nAssessment: %s%nDeadline: %s%nWeight: %s%n" +
                        "(you're an academic weapon, go get em <3)",
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

    /**
     * Helper function to get the notify date for a given assignment.
     * @param assignment the assignment to generate the notify date for
     * @return the notification date
     */
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

    /// /// /// /// ///

    /**
     * Send out a notifcation to the user's email.
     * @param newSession the server session.
     * @param mimeMessage the message that is to be sent.
     * @throws MessagingException
     */
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
