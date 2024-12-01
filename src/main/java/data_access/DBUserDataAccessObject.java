package data_access;

import entity.*;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The DAO for user data.
 */
public class DBUserDataAccessObject implements
//        SignupUserDataAccessInterface,
//        LoginUserDataAccessInterface,
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

    /// /// /// /// ///

//    @Override
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
    }

    /// /// /// /// ///

//    @Override
    public boolean existsByName(String username) {
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

//    @Override
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

    @Override
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
}
