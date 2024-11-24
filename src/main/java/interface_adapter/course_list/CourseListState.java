package interface_adapter.course_list;

import entity.Course;
import use_case.add_course.AddCourseDataAccessInterface;
import use_case.add_course.AddCourseOutputData;

import java.util.Collection;
import java.util.List;

public class CourseListState {

    private AddCourseDataAccessInterface addCourseDataAccessInterface;

    public List<Course> getCourses() { return addCourseDataAccessInterface.getCourses(); }
}
