package view;


import interface_adapter.edit_course.EditCourseController;
import interface_adapter.edit_course.EditCourseState;
import interface_adapter.edit_course.EditCourseViewModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * The view for when the user is editing a course.
 */
public class CourseEditView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "edit course";
    private final EditCourseViewModel editCourseViewModel;

    private final JTextField courseNameField = new JTextField(15);

    private final JButton editCourse;
    private final JButton cancel;
    private EditCourseController editCourseController;

    public CourseEditView(EditCourseViewModel editCourseViewModel) {
        this.editCourseViewModel = editCourseViewModel;
        this.editCourseViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("EditCourse");

        // Pink text
        title.setForeground(Color.getHSBColor(0.9F, 0F, 0.05F));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));

        // Set up view formatting
        this.setBackground(Color.getHSBColor(0.9F, 0.2F, 1F));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final LabelTextPanel courseNameInfo = new LabelTextPanel(new JLabel("Course Name: "), courseNameField);

        // Adding the buttons
        final JPanel buttons = new JPanel();
        editCourse = new StyledButton("Edit Course");
        buttons.add(editCourse);
        cancel = new StyledButton("Cancel");
        buttons.add(cancel);
        buttons.setBackground(Color.getHSBColor(0.9F, 0F, 0.05F));

        // Add button functionality
        editCourse.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(editCourse)) {
                            final EditCourseState currentState = editCourseViewModel.getState();

                            editCourseController.execute(currentState.getCourseName(),
                                    currentState.getCourseCode());
                        }
                    }
                }
        );

        cancel.addActionListener(this);

        courseNameField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final EditCourseState currentState = editCourseViewModel.getState();
                currentState.setCourseName(courseNameField.getText());
                editCourseViewModel.setState(currentState);
            }
            @Override
            public void insertUpdate(DocumentEvent e) {documentListenerHelper();}

            @Override
            public void removeUpdate(DocumentEvent e) {documentListenerHelper();}

            @Override
            public void changedUpdate(DocumentEvent e) {documentListenerHelper();}
        });



        this.add(title);
        this.add(courseNameInfo);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click" + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    final EditCourseState currentState = (EditCourseState) evt.getNewValue();
    setFields(currentState);
    }

    public void setFields(EditCourseState state) {
        courseNameField.setText(state.getCourseName());
    }

    public String getViewName() { return viewName; }

    public void setEditCourseController(EditCourseController editCourseController) {
        this.editCourseController = editCourseController;
    }
}
