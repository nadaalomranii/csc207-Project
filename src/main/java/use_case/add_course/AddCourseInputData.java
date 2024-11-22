package use_case.add_course;

import entity.Assignment;


/**
 * The input data for the Add Course Use Case.
 */
public class AddCourseInputData {

    private final String name;
    private final String code;

    public AddCourseInputData(String name, String code) {
        this.name = name;
        this.code = code;
    }

    String getName() {
        return name;
    }

    String getCode() {
        return code;
    }

}
