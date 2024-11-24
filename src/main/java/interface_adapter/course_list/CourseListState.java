package interface_adapter.course_list;

import entity.Course;

public class CourseListState {

    private Course[] courses;

    public Course[] getCourses() {return courses;}
    public void addCourses(Course[] courses) {this.courses = courses;}

}
