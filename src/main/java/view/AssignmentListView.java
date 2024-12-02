package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.util.List;

import javax.mail.MessagingException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import entity.Assignment;
import interface_adapter.add_assignment.addAssignmentController;
import interface_adapter.add_assignment.AddAssignmentViewModel;
import interface_adapter.add_assignment.AddAssignmentState;
import interface_adapter.assignment_list.AssignmentListViewModel;
import interface_adapter.assignment_list.AssignmentListState;
import interface_adapter.delete_assignment.DeleteAssignmentController;
import interface_adapter.delete_assignment.DeleteAssignmentState;
import interface_adapter.delete_assignment.DeleteAssignmentViewModel;
import interface_adapter.delete_course.DeleteCourseController;
import interface_adapter.delete_course.DeleteCourseViewModel;
import interface_adapter.send_notification.SendNotificationState;
import interface_adapter.send_notification.SendNotificationViewModel;
import interface_adapter.send_notification.SendNotificatonController;

/**
 * The view for when the user is adding a course.
 */
public class AssignmentListView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Assignment List";
    private final AssignmentListViewModel assignmentListViewModel;
    private final SendNotificationViewModel sendNotificationViewModel;
    private final DeleteCourseViewModel deleteCourseViewModel;

    private final JButton addAssignment;
    private final JButton deleteAssignment;
    private final JButton scheduleNotification;
    private final JButton deleteCourse;

    private SendNotificatonController sendNotificationController;
    private DeleteCourseController deleteCourseController;
    private DeleteAssignmentController deleteAssignmentController;

    // NEW FOR TABLE
    private final JTable assignmentTable; // The table to display assignment data
    private final DefaultTableModel tableModel; // Model to manage table data

    public AssignmentListView(AssignmentListViewModel assignmentListViewModel) {
        this.assignmentListViewModel = assignmentListViewModel;
        this.deleteCourseViewModel = new DeleteCourseViewModel();
        this.sendNotificationViewModel = new SendNotificationViewModel();
        this.assignmentListViewModel.addPropertyChangeListener(this);

        this.setBackground(Color.getHSBColor(28, 73, 69));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final JLabel title = new JLabel("Add Assignment");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add buttons
        final JPanel buttons = new JPanel();
        addAssignment = new JButton("Add Assignment");
        buttons.add(addAssignment);
        deleteAssignment = new JButton("Delete Assignment");
        buttons.add(deleteAssignment);
        deleteAssignment.addActionListener(this);
        scheduleNotification = new JButton("Schedule Emails for New Assignments");
        deleteCourse = new JButton("Delete Course");
        buttons.add(deleteCourse);

        // Button Functionality
        addAssignment.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(addAssignment)) {
                            final AddAssignmentState currentState = AddAssignmentViewModel.getState();

                            try {
                                addAssignmentController.execute(
                                        currentState.getAssignmentName(),
                                        currentState.getDueDate(),
                                        currentState.getGrade(),
                                        currentState.getWeight(),
                                        currentState.getCourse(),
                                        currentState.getUser()
                                );
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
        );

        deleteAssignment.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(deleteAssignment)) {
                            final DeleteAssignmentState currentState = DeleteAssignmentViewModel.getState();

                            int selectedRow = assignmentTable.getSelectedRow();
                            if (selectedRow != -1) { // Ensure a row is selected
                                // confirm user wants to delete
                                int confirm = JOptionPane.showConfirmDialog(
                                        null,
                                        "Are you sure you want to delete this assignment?",
                                        "Delete Confirmation",
                                        JOptionPane.YES_NO_OPTION
                                );

                                // run delete assignment use case and remove the row
                                if (confirm == JOptionPane.YES_OPTION) {
                                    tableModel.removeRow(selectedRow); // Remove the selected row
                                    // delete assignment use case
                                    deleteAssignmentController.execute(currentState.getAssignmentName(),
                                            currentState.getCourse(),
                                            currentState.getUser());
                                    JOptionPane.showMessageDialog(null, "Assignment deleted successfully.");
                                }
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "No assignment selected. Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
        );

        scheduleNotification.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(scheduleNotification)) {
                            final SendNotificationState currentState = sendNotificationViewModel.getState();

                            // run sendNotification Use Case
                            try {
                                sendNotificationController.execute(
                                        currentState.getUser(),
                                        currentState.getCourse(),
                                        currentState.getAssignments());
                            } catch (MessagingException e) {
                                throw new RuntimeException(e);
                            }
                        }

                    }
                });

// Delete Course
        deleteCourse.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(deleteCourse)) {
                            final AssignmentListState currentState = assignmentListViewModel.getState();

                            // Executes the delete course use case.
                            deleteCourseController.execute(currentState.getCourse().getCode(),
                                    currentState.getCourse().getName(),
                                    currentState.getUser());
                        }
                    }
                }
        );

        this.add(title);
        this.add(buttons);

        // Table Panel
        String[] columnNames = {"Name", "Grade", "Weight", "Due Date"}; // Define table headers
        tableModel = new DefaultTableModel(columnNames, 0); // Initialize with no rows
        assignmentTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(assignmentTable); // Add table to scroll pane
        this.add(tableScrollPane, BorderLayout.CENTER); // Add table to the center of the layout
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == addAssignment) {
            // Get data from input fields
            String assignmentNameFieldText = assignmentNameField.getText();
            String assignmentGradeFieldText = assignmentGradeField.getText();
            String assignmentWeightFieldText = assignmentWeightField.getText();
            String assignmentDueDateFieldText = assignmentDueDateField.getText();

            // Add a new row to the table
            tableModel.addRow(new Object[]{assignmentNameFieldText, assignmentGradeFieldText, assignmentWeightFieldText
                    , assignmentDueDateFieldText});

            // Clear the input fields (reset to blank)
            assignmentNameField.setText("");
            assignmentGradeField.setText("");
            assignmentWeightField.setText("");
            assignmentDueDateField.setText("");

        }

        else


        } else if (evt.getSource() == deleteAssignment) {
            // Handle delete assignment button click

    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO: check this is right
        // Do we need this?
        // if (evt.getPropertyName().equals("state")) {}

        // Pop ups for SendNotifications Use Case
        if (evt.getPropertyName().equals("notifications scheduled")) {
            // can i do this or does the state have to be AssignmentListState
            final SendNotificationState state = (SendNotificationState) evt.getNewValue();
            List<Assignment> scheduledAssignments = state.getNewlyScheduledAssignments();
            StringBuilder assignmentString = new StringBuilder();
            for (Assignment assignment : scheduledAssignments) {
                assignmentString.append(assignment.getName()).append("\n");
            }
            JOptionPane.showMessageDialog(null, ("Notifications scheduled for: \n" + assignmentString.toString() ));
        }
        else if (evt.getPropertyName().equals("No new assignments to schedule")) {
            final SendNotificationState state = (SendNotificationState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, ("No new assignments to schedule for: \n" + state.getCourse()));
        }
    }

    // TODO: do we need this?
    //private void setFields(AssignmentListState state) {}

    public String getViewName() {
        return viewName;
    }

    public void setDeleteAssignmentController(DeleteAssignmentController deleteAssignmentController) {
    }

    public void setDeleteCourseController(DeleteCourseController deleteCourseController) {
        this.deleteCourseController = deleteCourseController;
    }
}
