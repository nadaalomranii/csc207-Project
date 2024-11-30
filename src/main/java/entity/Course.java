package entity;

import java.util.List;

/**
 * The representation of a course in our program.
 */
public interface Course {

    /**
     * Returns the name of the course.
     * @return the name of the course.
     */
    String getName();

    /**
     * Returns the name of the course code.
     * @return the name of the course code.
     */
//    String getCourseName();

    /**
     * Returns the list of the assignments.
     * @return the list of the assignments.
    List<Assignment> getAssignments();
     */

    /**
     * Returns the code of the course.
     * @return the code of the course.
     */
    String getCode();

    /**
     * Changes the name of the course
     */
    void changeName(String newName);
}