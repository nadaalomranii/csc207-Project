package data_access;

import entity.Assignment;
import entity.CommonAssignment;
import entity.Course;
import use_case.add_assignment.AddAssignmentCourseDataAccessInterface;
import use_case.add_course.AddCourseDataAccessInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataAccessInterface implements AddCourseDataAccessInterface, AddAssignmentCourseDataAccessInterface {

    private final Map<String, Map<Course, List<CommonAssignment>>> courses = new HashMap<>();

    @Override
    public void saveAssignment(CommonAssignment assignment) {
        courses.get(assignment.getCourseCode()).put(assignment.getCourse(), assignment);
    }

    @Override
    public boolean existsByCode(String code) {
        return courses.containsKey(code);
    }

    @Override
    public void save(Course course) {
        courses.put(course.getCode(), new HashMap<>());
    }
}
