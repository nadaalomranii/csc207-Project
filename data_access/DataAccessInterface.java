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

    private final Map<String, Map<Course, List<Assignment>>> courses = new HashMap<>();

    @Override
    public void saveAssignment(Assignment assignment, Course course) {
        // The course already exists in courses
        // Add the new assignment to the current assignments
        List<Assignment> currentAssignments = courses.get(course.getCode()).get(course);
        currentAssignments.add(assignment);

        courses.get(course.getCode()).put(course, currentAssignments);
    }

    @Override
    public boolean existsByCode(String code) {
        return courses.containsKey(code);
    }

    @Override
    public void saveCourse(Course course) {
        courses.put(course.getCode(), new HashMap<>());
    }
}
