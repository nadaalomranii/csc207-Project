package use_case.add_course;

import entity.Assignment;
import entity.User;


/**
 * The input data for the Add Course Use Case.
 */
public class AddCourseInputData {

    private final String name;
    private final String code;
    private final User user;

    public AddCourseInputData(String name, String code, User user) {
        this.name = name;
        this.code = code;
        this.user = user;
    }

    String getName() { return name; }
    String getCode() {
        return code;
    }
    User getUser() { return user; }

}
