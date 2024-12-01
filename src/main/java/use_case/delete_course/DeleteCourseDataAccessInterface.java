package use_case.delete_course;

import entity.Course;
import entity.User;

public interface DeleteCourseDataAccessInterface {
    void deleteCourse(Course course, User user);
}
