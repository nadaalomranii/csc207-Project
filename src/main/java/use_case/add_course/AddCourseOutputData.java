package use_case.add_course;

import entity.Course;

import java.util.ArrayList;
import java.util.List;

import static java.util.function.Predicate.not;

/**
 * Output Data for the Add Course Use Case.
 */
public class AddCourseOutputData {

    private Course course;
    private boolean useCaseFailed;
    private List<Course> courses;

    public AddCourseOutputData(Course course, boolean useCaseFailed, List<Course> courses) {
        this.course = course;
        this.useCaseFailed = useCaseFailed;
        this.courses = courses;
    }

    public AddCourseOutputData() {
        this.course = null;
        this.useCaseFailed = true;
        this.courses = new ArrayList<>();
    }

    public Course getCourse() { return course; }
    public String getName() {
        if (useCaseFailed) {return null; };
        return course.getName();
    }

    public String getCode() {
        if (useCaseFailed) {return null;}
        return course.getCode(); }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public List<Course> getCourses() {
        return courses;
    }

}
