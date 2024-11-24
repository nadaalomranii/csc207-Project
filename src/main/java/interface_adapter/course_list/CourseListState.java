package interface_adapter.course_list;

import entity.Course;
import use_case.add_course.AddCourseOutputData;

public class CourseListState {

    private Course course;

    // public Course[] getCourses() {return courses;}
    public void setCourse(AddCourseOutputData course) { this.course = course.getCourse(); }
    public Course getCourse() { return course; }
    //public void addCourses(Course[] courses) {this.courses = courses;}

}
