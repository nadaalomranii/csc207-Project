package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import entity.Assignment;
import interface_adapter.ViewManagerModel;
import interface_adapter.assignment_list.AssignmentListController;
import interface_adapter.add_assignment.AddAssignmentController;
import interface_adapter.add_assignment.AddAssignmentViewModel;
import interface_adapter.assignment_list.AssignmentListViewModel;
import interface_adapter.assignment_list.AssignmentListState;
import interface_adapter.course_list.CourseListViewModel;
import interface_adapter.delete_assignment.DeleteAssignmentController;
import interface_adapter.delete_course.DeleteCourseController;
import interface_adapter.delete_course.DeleteCourseViewModel;
import interface_adapter.edit_course.EditCourseController;
import interface_adapter.edit_course.EditCourseViewModel;
import interface_adapter.send_notification.SendNotificationState;
import interface_adapter.send_notification.SendNotificationViewModel;
import interface_adapter.send_notification.SendNotificatonController;

/**
 * The view for the assignments of a course.
 */
public class AssignmentListView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Assignment List";
    private final AssignmentListViewModel assignmentListViewModel;
    private final AddAssignmentViewModel addAssignmentViewModel;
    private final SendNotificationViewModel sendNotificationViewModel;
    private final DeleteCourseViewModel deleteCourseViewModel;
    private final EditCourseViewModel editCourseViewModel;
    private final CourseListViewModel courseListViewModel;
    private final ViewManagerModel viewManagerModel;

    private final JButton addAssignment;
    private final JButton deleteAssignment;
    private final JButton scheduleNotification;
    private final JButton deleteCourse;
    private final JButton editCourse;
    private final JButton backButton;

    private SendNotificatonController sendNotificationController;
    private DeleteCourseController deleteCourseController;
    private AssignmentListController assignmentListController;
    private DeleteAssignmentController deleteAssignmentController;
    private AddAssignmentController addAssignmentController;

    // NEW FOR TABLE
    private JScrollPane jScrollPane;
    final JPanel tablePanel = new JPanel();
    final JPanel title = new JPanel();
    private DefaultTableModel assignmentTableModel;
    private JTable assignmentTable;
    private String[] columns;
    private String[][] assignmentList = new String[0][0];

    public AssignmentListView(AssignmentListViewModel assignmentListViewModel,
                              AddAssignmentViewModel addAssignmentViewModel,
                              DeleteCourseViewModel deleteCourseViewModel,
                              ViewManagerModel viewManagerModel,
                              EditCourseViewModel editCourseViewModel,
                              CourseListViewModel courseListViewModel,
                              SendNotificationViewModel sendNotificationViewModel) {

        this.assignmentListViewModel = assignmentListViewModel;
        this.addAssignmentViewModel = addAssignmentViewModel;
        this.assignmentListViewModel.addPropertyChangeListener(this);
        this.deleteCourseViewModel = deleteCourseViewModel;
        this.viewManagerModel = viewManagerModel;
        this.editCourseViewModel = editCourseViewModel;
        this.courseListViewModel = courseListViewModel;
        this.sendNotificationViewModel = new SendNotificationViewModel();

        // set up
        this.setBackground(Color.getHSBColor(0.9F, 0.2F, 1F));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        // empty table
//        String[] columnNames = {"Name", "Grade", "Weight", "Due Date"}; // Define table headers
//        tableModel = new DefaultTableModel(columnNames, 0); // Initialize with no rows
//        assignmentTable = new JTable(tableModel);
//        JScrollPane tableScrollPane = new JScrollPane(assignmentTable); // Add table to scroll pane
//        this.add(tableScrollPane, BorderLayout.CENTER); // Add table to the center of the layout

//        final JPanel tablePanel = new JPanel();

        //empty table

        columns = new String[]{"Assignment", "Due Date", "Weight", "Grade"};
        assignmentTableModel = new DefaultTableModel(assignmentList, columns);
        assignmentTable = new JTable(assignmentTableModel);
        JScrollPane tableScrollPane = new JScrollPane(assignmentTable); // Add table to scroll pane
        assignmentTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        // total grade place holder
        JTable totalGrade = new JTable(1,2);
        float grade = 0 ;
        String totalGradeLabel = "Total Grade Acquired";
        totalGrade.setValueAt(totalGradeLabel, 0, 0);
        totalGrade.setValueAt(grade, 0, 1);
        totalGrade.getColumnModel().getColumn(0).setPreferredWidth(200);

        // add assignment table and total grade placeholder to panel
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);
        tablePanel.add(totalGrade,BorderLayout.CENTER);

        // Add buttons
        final JPanel buttons = new JPanel();
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);

        addAssignment = new JButton("Add Assignment");
        buttons.add(addAssignment);

        deleteAssignment = new JButton("Delete Assignment");
        buttons.add(deleteAssignment);

        scheduleNotification = new JButton("Schedule Emails for New Assignments");
        buttons.add(scheduleNotification);

        deleteCourse = new JButton("Delete Course");
        buttons.add(deleteCourse);

        editCourse = new JButton("Edit Course");
        buttons.add(editCourse);
        backButton = new JButton("Back");
        buttons.add(backButton);


        //set up screen
        this.add(tablePanel);
        this.add(title);
        this.add(buttons);

        //Button Functionality
        //CHECK: When we click the add assignment button, we want to go to the add assignmennt view model. In the add assignment view model, we get the actual state.
        // Button Functionality

        // SET FIELDS
        addAssignment.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        assignmentListController.switchToAddAssignmentView(viewManagerModel, addAssignmentViewModel, assignmentListViewModel);
                    }
                }
        );

        // SET FIELDS
        deleteAssignment.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
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
                                // delete assignment use case
                                deleteAssignmentController.execute((String) assignmentTableModel.getValueAt(selectedRow, 0), assignmentListViewModel.getState().getCourse(), assignmentListViewModel.getState().getUser());
                                assignmentTableModel.removeRow(selectedRow); // Remove the selected row
                                JOptionPane.showMessageDialog(null, "Assignment deleted successfully.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "No assignment selected. Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

        scheduleNotification.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(scheduleNotification)) {
                            final SendNotificationState currentState = sendNotificationViewModel.getState();

                            // run sendNotification Use Case
                            try {
                                sendNotificationController.execute(assignmentListViewModel.getState().getUser(),
                                        assignmentListViewModel.getState().getCourse(),
                                        assignmentListViewModel.getState().getAssignmentList());
                            } catch (MessagingException e) {
                                throw new RuntimeException(e);
                            }
                        }

                    }
                });
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Pop ups for SendNotifications Use Case

        // Updates the assignment list state?
        final AssignmentListState currentState = (AssignmentListState) evt.getNewValue();
        assignmentListViewModel.setState(currentState);
        setFields(currentState);

        if (evt.getPropertyName().equals("notifications scheduled")) {
            // can i do this or does the state have to be AssignmentListState
            final SendNotificationState state = (SendNotificationState) evt.getNewValue();
            List<Assignment> scheduledAssignments = state.getNewlyScheduledAssignments();
            StringBuilder assignmentString = new StringBuilder();
            for (Assignment assignment : scheduledAssignments) {
                assignmentString.append(assignment.getName()).append("\n");
            }
            JOptionPane.showMessageDialog(null, ("Notifications scheduled for: \n" + assignmentString.toString()));
        } else if (evt.getPropertyName().equals("No new assignments to schedule")) {
            final SendNotificationState state = (SendNotificationState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, ("No new assignments to schedule for: \n" + state.getCourse()));
        }
        else if (evt.getPropertyName().equals("delete assignment")) {
            setFields(currentState);
        }
    }

    private void setFields(AssignmentListState state) {
        assignmentListViewModel.setState(state);
        tablePanel.removeAll();
        title.removeAll();

        //Add the title
        title.add(new JLabel("Assignments for " + state.getCourse().getName()));

        // CREATE A TABLE
        // date format
        String pattern = "dd-MM-yyyy";
        DateFormat dateFormat = new SimpleDateFormat(pattern);

        List<Assignment> assignmentObjects = assignmentListViewModel.getState().getCourse().getAssignments();

        //get info for table
        String[][] assignmentList = new String[assignmentObjects.size()][4];
        if (!assignmentObjects.isEmpty()) {
            int counter = 0;
            for (Assignment assignment : assignmentObjects) {
                String[] assignmentInfo = new String[4];
                assignmentInfo[0] = assignment.getName();
                assignmentInfo[1] = dateFormat.format(assignment.getDueDate());
                assignmentInfo[2] = String.valueOf(assignment.getWeight());
                assignmentInfo[3] = String.valueOf(assignment.getGrade());
                assignmentList[counter] = assignmentInfo;
                counter += 1;
            }
        }
        // table
        columns = new String[]{"Assignment", "Due Date", "Weight", "Grade"};
        assignmentTableModel = new DefaultTableModel(assignmentList, columns);
        assignmentTable = new JTable(assignmentTableModel);
        jScrollPane = new JScrollPane(assignmentTable);
        assignmentTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablePanel.add(jScrollPane);
//        this.add(tablePanel);

        float grade = 0;
        if (!assignmentObjects.isEmpty()) {
            for (Assignment assignment: assignmentObjects) {
                grade += (assignment.getWeight()/100 * assignment.getGrade());
            }
        }

        JTable totalGrade = new JTable(1,2);
        String totalGradeLabel = "Total Grade Acquired";
        totalGrade.setValueAt(totalGradeLabel, 0, 0);
        totalGrade.setValueAt(grade, 0, 1);

        totalGrade.getColumnModel().getColumn(0).setPreferredWidth(200);
        tablePanel.add(totalGrade,BorderLayout.CENTER);
        this.add(tablePanel);


        deleteCourse.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        assignmentListViewModel.setState(state);
                        final AssignmentListState currentState = assignmentListViewModel.getState();
                        // Executes the delete course use case
                        deleteCourseController.execute(currentState.getCourse().getCode(),
                                currentState.getCourse().getName(),
                                currentState.getUser());
                    }
                 });
        editCourse.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        assignmentListViewModel.setState(state);
                        final AssignmentListState currentState = assignmentListViewModel.getState();
                        // Switch to the edit course view
                        assignmentListController.switchToEditCourseView(viewManagerModel, editCourseViewModel, assignmentListViewModel);
                    }
                }
        );

        backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        assignmentListViewModel.setState(state);
                        // Switches to the course list view
                        assignmentListController.switchToCourseListView(viewManagerModel, courseListViewModel, assignmentListViewModel);
                    }
                }
        );

        deleteAssignment.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
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
                                // delete assignment use case
                                deleteAssignmentController.execute((String) assignmentTableModel.getValueAt(selectedRow, 0), assignmentListViewModel.getState().getCourse(), assignmentListViewModel.getState().getUser());
                                tablePanel.removeAll();
                                String pattern = "dd-MM-yyyy";
                                DateFormat dateFormat = new SimpleDateFormat(pattern);

                                List<Assignment> assignmentObjects = assignmentListViewModel.getState().getCourse().getAssignments();

                                //get info for table
                                String[][] assignmentList = new String[assignmentObjects.size()][4];
                                if (!assignmentObjects.isEmpty()) {
                                    int counter = 0;
                                    for (Assignment assignment : assignmentObjects) {
                                        String[] assignmentInfo = new String[4];
                                        assignmentInfo[0] = assignment.getName();
                                        assignmentInfo[1] = dateFormat.format(assignment.getDueDate());
                                        assignmentInfo[2] = String.valueOf(assignment.getWeight());
                                        assignmentInfo[3] = String.valueOf(assignment.getGrade());
                                        assignmentList[counter] = assignmentInfo;
                                        counter += 1;
                                    }
                                }
                                // table
                                columns = new String[]{"Assignment", "Due Date", "Weight", "Grade"};
                                assignmentTableModel = new DefaultTableModel(assignmentList, columns);
                                assignmentTable = new JTable(assignmentTableModel);
                                jScrollPane = new JScrollPane(assignmentTable);
                                assignmentTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
                                tablePanel.add(jScrollPane);
//                                assignmentTableModel.removeRow(selectedRow); // Remove the selected row
                                JOptionPane.showMessageDialog(null, "Assignment deleted successfully. In order to update your assignment list, please reload this page.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "No assignment selected. Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
        }

    public String getViewName() {
        return viewName;
    }

    public void setDeleteAssignmentController(DeleteAssignmentController deleteAssignmentController) {
        this.deleteAssignmentController = deleteAssignmentController;
    }

    public void setDeleteCourseController(DeleteCourseController deleteCourseController) {
        this.deleteCourseController = deleteCourseController;
    }

    public void setAssignmentListController(AssignmentListController assignmentListController) {
        this.assignmentListController = assignmentListController;
    }
//
//    public void setEditAssignmentController(EditAss)
}
