package data_access;

import entity.Assignment;
import entity.Course;
import use_case.add_assignment.AddAssignmentCourseDataAccessInterface;
import use_case.add_course.AddCourseDataAccessInterface;
import use_case.delete_assignment.DeleteAssignmentDataAccessInterface;
import use_case.delete_course.DeleteCourseDataAccessInterface;

import java.util.*;

public class DataAccessInterface implements
        AddCourseDataAccessInterface,
        AddAssignmentCourseDataAccessInterface,
        DeleteCourseDataAccessInterface,
        DeleteAssignmentDataAccessInterface,{

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
}
