package use_case.add_course;

import entity.Course;

import static java.util.function.Predicate.not;

/**
 * Output Data for the Add Course Use Case.
 */
public class AddCourseOutputData {

    private Course course;
    private boolean useCaseFailed;

    public AddCourseOutputData(Course course, boolean useCaseFailed) {
        this.course = course;
        this.useCaseFailed = useCaseFailed;
    }

    public AddCourseOutputData() {
        this.course = null;
        this.useCaseFailed = true;
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
}
