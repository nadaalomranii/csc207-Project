package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.*;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_course.AddCourseState;
import interface_adapter.add_course.AddCourseViewModel;
import interface_adapter.course_list.CourseListController;
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
    private CourseListController courseListController;

    private JButton courseButton;
    private final JButton addCourseButton;


    public CourseListView(CourseListViewModel courseListViewModel,
                          List<Course> courseList,
                          ViewManagerModel viewManagerModel,
                          AddCourseViewModel addCourseViewModel) {
        this.courseListViewModel = courseListViewModel;
        this.courseListViewModel.addPropertyChangeListener(this);
        this.courseList = courseList;

        // title
        final JLabel title = new JLabel("Courses");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Pink text
        title.setForeground(Color.getHSBColor(0.9F, 0F, 0.05F));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));

        // Set up view formatting
        this.setBackground(Color.getHSBColor(0.9F, 0.2F, 1F));
        FlowLayout flowLayout = new FlowLayout();
        this.setLayout(flowLayout);
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Course buttons
        final JPanel allCoursesPanel = new JPanel();
        List<Course> courses = courseListViewModel.getState().getCourses();
        if (courses != null) {
            for (Course course : courses) {
                courseButton = new JButton(course.getCode());
                allCoursesPanel.add(courseButton);

                // add button functionality
                courseButton.addActionListener(
                        new ActionListener() {
                            public void actionPerformed(ActionEvent evt) {
                                if (evt.getSource().equals(courseButton)) {
                                    // TODO: go to assignment list view of this course
                                }
                            }
                        }
                );
            }
        }
        allCoursesPanel.setAlignmentX(Component.CENTER_ALIGNMENT);


        // add course button
        final JPanel addCoursePanel = new JPanel();
        addCourseButton = new JButton("Add Course");
        addCoursePanel.add(addCourseButton);
        addCourseButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(addCourseButton)) {
                            // TODO: go to add course view
                        }
                    }
                }
        );
        addCoursePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        addCourseButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                        courseListController.switchToCourseListView(viewManagerModel, addCourseViewModel, courseListViewModel);
                    }
                }
        );


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
        // TODO: are there any fields to set?
    }

    public String getViewName() {
        return viewName;
    }

    public void setCourseListController(CourseListController courseListController) {
        this.courseListController = courseListController;
    }
}
