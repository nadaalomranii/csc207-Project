package use_case.add_course;

import entity.Course;

/**
 * Output Data for the Add Course Use Case.
 */
public class AddCourseOutputData {

    private final Course course;

    private final boolean useCaseFailed;

    public AddCourseOutputData(Course course, boolean useCaseFailed) {
        this.course = course;
        this.useCaseFailed = useCaseFailed;
    }
    public Course getCourse() { return course; }
    public String getName() {
        return course.getName();
    }
    public String getCode() { return course.getCode(); }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
