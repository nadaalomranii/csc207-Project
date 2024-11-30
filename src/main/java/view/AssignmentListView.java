package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.assignment_list.AssignmentListViewModel;
import interface_adapter.assignment_list.AssignmentListState;
import interface_adapter.delete_assignment.DeleteAssignmentController;

/**
 * The view for when the user is adding a course.
 */
public class    AssignmentListView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Assignment List";
    private final AssignmentListViewModel assignmentListViewModel;

    private final JTextField courseNameField = new JTextField(15);
    private final JTextField courseCodeField = new JTextField(15);

    private final JButton addCourse;
    private final JButton cancel;

    public AssignmentListView(AssignmentListViewModel assignmentListViewModel) {
        this.assignmentListViewModel = assignmentListViewModel;
        this.assignmentListViewModel.addPropertyChangeListener(this);

        this.setBackground(Color.getHSBColor(28, 73, 69));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final JLabel title = new JLabel("Add Course");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel courseNameInfo = new LabelTextPanel(new JLabel("Course Name: "), courseNameField);
        final LabelTextPanel courseCodeInfo = new LabelTextPanel(new JLabel("Course Code: "), courseCodeField);

        // Add buttons
        final JPanel buttons = new JPanel();
        addCourse = new JButton("Add Course");
        buttons.add(addCourse);
        cancel = new JButton("Cancel");
        buttons.add(cancel);

        this.add(title);
        this.add(courseNameInfo);
        this.add(courseCodeInfo);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {System.out.println("Click " + evt.getActionCommand());}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final AssignmentListState state = (AssignmentListState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(AssignmentListState state) {
    }

    public String getViewName() {
        return viewName;
    }

    public void setDeleteAssignmentController(DeleteAssignmentController deleteAssignmentController) {
    }
}
