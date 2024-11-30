package view;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.add_assignment.AddAssignmentState;
import interface_adapter.add_assignment.AddAssignmentViewModel;
import interface_adapter.add_assignment.AddAssignmentController;
import raven.datetime.component.date.DatePicker;

import static java.lang.String.valueOf;

/**
 * The view for when the user is adding a course.
 */
public class AssignmentAddView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Add Assignment";
    private final AddAssignmentViewModel addAssignmentViewModel;

    private final JTextField assignmentNameField = new JTextField(15);
    private final JTextField assignmentGradeField = new JTextField(15);
    private final JTextField assignmentWeightField = new JTextField(15);
    private final JTextField assignmentDueDateField = new JTextField(15);
    

    private final JButton addAssignment;
    private final JButton cancel;
    private AddAssignmentController addAssignmentController;
    
    public AssignmentAddView(AddAssignmentViewModel addAssignmentViewModel) {
        this.addAssignmentViewModel = addAssignmentViewModel;
        this.addAssignmentViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Add Assignment");
        // Pink text
        title.setForeground(Color.getHSBColor(0.9F, 0F, 0.05F));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));

        // Set up view formatting
        this.setBackground(Color.getHSBColor(0.9F, 0.2F, 1F));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final LabelTextPanel assignmentNameInfo = new LabelTextPanel(new JLabel("Assignment Name: "), assignmentNameField);
        final LabelTextPanel assignmentGradeInfo = new LabelTextPanel(new JLabel("Obtained Grade: "), assignmentGradeField);
        final LabelTextPanel assignmentWeightInfo = new LabelTextPanel(new JLabel("Assignment Name: "), assignmentWeightField);
        final LabelTextPanel assignmentDueDateInfo = new LabelTextPanel(new JLabel("Due Date: "), assignmentDueDateField);

        // Add buttons
        final JPanel buttons = new JPanel();
        addAssignment = new StyledButton("Add Assignment");
        buttons.add(addAssignment);
        cancel = new StyledButton("Cancel");
        buttons.add(cancel);
        buttons.setBackground(Color.getHSBColor(0.9F, 0F, 0.05F));

        // Add button functionality
        addAssignment.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(addAssignment)) {
                            final AddAssignmentState currentState = addAssignmentViewModel.getState();

                            try {
                                addAssignmentController.execute(
                                        currentState.getAssignmentName(),
                                        currentState.getDueDate(),
                                        currentState.getGrade(),
                                        currentState.getWeight(),
                                        currentState.getCourse()
                                );
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
        );

        cancel.addActionListener(this);

        assignmentNameField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final AddAssignmentState currentState = addAssignmentViewModel.getState();
                currentState.setAssignmentName(assignmentNameField.getText());
                addAssignmentViewModel.setState(currentState);
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

        assignmentGradeField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final AddAssignmentState currentState = addAssignmentViewModel.getState();
                currentState.setGrade(assignmentNameField.getText());
                addAssignmentViewModel.setState(currentState);
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

        assignmentWeightField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final AddAssignmentState currentState = addAssignmentViewModel.getState();
                currentState.setWeight(assignmentNameField.getText());
                addAssignmentViewModel.setState(currentState);
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

        assignmentDueDateField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() throws ParseException {
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                final AddAssignmentState currentState = addAssignmentViewModel.getState();
                String assignmentDueDateInput = assignmentDueDateField.getText();
                Date date = formatter.parse(assignmentDueDateInput);
                currentState.setDueDate(date);
                addAssignmentViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    documentListenerHelper();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    documentListenerHelper();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    documentListenerHelper();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        this.add(title);
        this.add(assignmentNameInfo);
        this.add(assignmentGradeInfo);
        this.add(assignmentWeightInfo);
        this.add(assignmentDueDateInfo);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {System.out.println("Click " + evt.getActionCommand());}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final AddAssignmentState state = (AddAssignmentState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(AddAssignmentState state) {
        assignmentNameField.setText(state.getAssignmentName());
        assignmentGradeField.setText(state.getGrade());
        assignmentWeightField.setText(state.getWeight());
        //Here
        String dateText = state.getDueDate().toString();
        assignmentDueDateField.setText(dateText);
    }

    public String getViewName() {
        return viewName;
    }

    public void setAddAssignmentController(AddAssignmentController addAssignmentController) {
        this.addAssignmentController = addAssignmentController;
    }
}
