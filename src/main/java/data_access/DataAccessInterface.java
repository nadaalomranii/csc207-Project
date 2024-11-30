package data_access;

import entity.Assignment;
import entity.Course;
import use_case.add_assignment.AddAssignmentCourseDataAccessInterface;
import use_case.add_course.AddCourseDataAccessInterface;
import use_case.delete_course.DeleteCourseDataAccessInterface;
import use_case.edit_course.EditCourseDataAccessInterface;

import java.util.*;

public class DataAccessInterface implements AddCourseDataAccessInterface, AddAssignmentCourseDataAccessInterface, DeleteCourseDataAccessInterface, EditCourseDataAccessInterface {
    // The first key is the course code
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
    public void saveCourse(Course course) {
        // Adds the course code as a key
        courses.put(course.getCode(), new HashMap<>());
        // Adds the course corresponding to that course code
        courses.get(course.getCode()).put(course, new ArrayList<>());
    }

    @Override
    public void deleteCourse(Course course) {
        courses.remove(course.getCode());
    }

    @Override
    public void editCourse(Course course) {
        Set<Course> currentCourse = courses.get(course.getCode()).keySet();
        // This set only contains one course
        for (Course c : currentCourse) {
            c.changeName(course.getName());
        }
    }

    @Override
    public String checkName(String courseCode) {
        Set<Course> currentCourse = courses.get(courseCode).keySet();
        // This set only contains one course
        String courseName = "";
        for (Course c : currentCourse) {
            courseName = c.getName();
        }
        return courseName;
    }
}
