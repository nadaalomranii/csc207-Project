package interface_adapter.course_list;

import entity.Course;
import entity.User;
import use_case.add_course.AddCourseDataAccessInterface;
import use_case.add_course.AddCourseOutputData;

import java.util.Collection;
import java.util.List;

public class CourseListState {

    private List<Course> courseList;
    private User user;

    public List<Course> getCourses() { return this.courseList; }

    public void setCourses(List<Course> courseList) { this.courseList = courseList; }

    public User getUser() { return this.user; }

    public void setUser(User user) { this.user = user; }

}
