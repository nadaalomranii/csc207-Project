package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.add_course.AddCourseState;
import interface_adapter.add_course.AddCourseViewModel;
import interface_adapter.add_course.AddCourseController;

/**
 * The view for when the user is adding a course.
 */
public class CourseAddView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Add Course";
    private final AddCourseViewModel addCourseViewModel;

    private final JTextField courseNameField = new JTextField(15);
    private final JTextField courseCodeField = new JTextField(15);

    private final JButton addCourse;
    private final JButton cancel;
    private AddCourseController addCourseController;

    public CourseAddView(AddCourseViewModel addCourseViewModel) {
        this.addCourseViewModel = addCourseViewModel;
        this.addCourseViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Add Course");
        // Pink text
        title.setForeground(Color.getHSBColor(0.9F, 0F, 0.05F));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));

        // Set up view formatting
        this.setBackground(Color.getHSBColor(0.9F, 0.2F, 1F));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final LabelTextPanel courseNameInfo = new LabelTextPanel(new JLabel("Course Name: "), courseNameField);
        final LabelTextPanel courseCodeInfo = new LabelTextPanel(new JLabel("Course Code: "), courseCodeField);

        // Add buttons
        final JPanel buttons = new JPanel();
        addCourse = new StyledButton("Add Course");
        buttons.add(addCourse);
        cancel = new StyledButton("Cancel");
        buttons.add(cancel);
        buttons.setBackground(Color.getHSBColor(0.9F, 0F, 0.05F));

        // Add button functionality
        addCourse.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(addCourse)) {
                            final AddCourseState currentState = addCourseViewModel.getState();

                            addCourseController.execute(
                                    currentState.getCourseName(),
                                    currentState.getCourseCode(),
                                    currentState.getUser()
                            );
                        }
                    }
                }
        );

        cancel.addActionListener(this);

        courseNameField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final AddCourseState currentState = addCourseViewModel.getState();
                currentState.setCourseName(courseNameField.getText());
                addCourseViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        courseCodeField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final AddCourseState currentState = addCourseViewModel.getState();
                currentState.setCourseCode(courseCodeField.getText());
                addCourseViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        this.add(title);
        this.add(courseNameInfo);
        this.add(courseCodeInfo);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {System.out.println("Click " + evt.getActionCommand());}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final AddCourseState state = (AddCourseState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(AddCourseState state) {
    }

    public String getViewName() {
        return viewName;
    }

    public void setAddCourseController(AddCourseController addCourseController) {
        this.addCourseController = addCourseController;
    }
}
