package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.*;

import interface_adapter.course_list.CourseListState;
import interface_adapter.course_list.CourseListViewModel;


import entity.Course;

/**
 * The view for when the user is adding a course.
 */
public class CourseListView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Course List";
    private final CourseListViewModel courseListViewModel;
    private final List<Course> courseList;

    private JButton courseButton;
    private final JButton addCourseButton;

    public CourseListView(CourseListViewModel courseListViewModel, List<Course> courseList) {
        this.courseListViewModel = courseListViewModel;
        this.courseListViewModel.addPropertyChangeListener(this);
        this.courseList = courseList;

        this.setBackground(Color.getHSBColor(28, 73, 69));
        FlowLayout flowLayout = new FlowLayout();
        this.setLayout(flowLayout);

        // title
        final JLabel title = new JLabel("Courses");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Course buttons
        final JPanel allCoursesPanel = new JPanel();
        if (this.courseList != null) {
            for (Course course : this.courseList) {
                courseButton = new JButton(course.getCode());
                allCoursesPanel.add(courseButton);
            }
        }
        allCoursesPanel.setAlignmentX(Component.CENTER_ALIGNMENT);


        // add course button
        final JPanel addCoursePanel = new JPanel();
        addCourseButton = new JButton("Add Course");
        addCoursePanel.add(addCourseButton);
        addCoursePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(title);
        this.add(allCoursesPanel);
        this.add(addCoursePanel);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {System.out.println("Click " + evt.getActionCommand());}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final CourseListState state = (CourseListState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(CourseListState state) {
    }

    public String getViewName() {
        return viewName;
    }

}
