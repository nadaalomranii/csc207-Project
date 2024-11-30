package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import interface_adapter.assignment_list.AssignmentListViewModel;
import interface_adapter.assignment_list.AssignmentListState;

/**
 * The view for when the user is adding a course.
 */
public class AssignmentListView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Assignment List";
    private final AssignmentListViewModel assignmentListViewModel;

    private final JTextField assignmentNameField = new JTextField(15);
    private final JTextField assignmentGradeField = new JTextField(15);
    private final JTextField assignmentWeightField = new JTextField(15);
    private final JTextField assignmentDueDateField = new JTextField(15);

    private final JButton addAssignment;
    private final JButton deleteAssignment;

    // NEW FOR TABLE
    private final JTable assignmentTable; // The table to display assignment data
    private final DefaultTableModel tableModel; // Model to manage table data

    public AssignmentListView(AssignmentListViewModel assignmentListViewModel) {
        this.assignmentListViewModel = assignmentListViewModel;
        this.assignmentListViewModel.addPropertyChangeListener(this);

        this.setBackground(Color.getHSBColor(28, 73, 69));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final JLabel title = new JLabel("Add Assignment");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel assignmentNameInfo = new LabelTextPanel(new JLabel("Assignment Name: "), assignmentNameField);
        final LabelTextPanel assignmentGradeInfo = new LabelTextPanel(new JLabel("Assignment Grade: "), assignmentGradeField);
        final LabelTextPanel assignmentWeightInfo = new LabelTextPanel(new JLabel("Assignment Weight: "), assignmentWeightField);
        final LabelTextPanel assignmentDueDateInfo = new LabelTextPanel(new JLabel("Assignment DueDate: "), assignmentDueDateField);

        // Add buttons
        final JPanel buttons = new JPanel();
        addAssignment = new JButton("Add Assignment");
        buttons.add(addAssignment);
        deleteAssignment = new JButton("Delete Assignment");
        buttons.add(deleteAssignment);
        deleteAssignment.addActionListener(this);

        this.add(title);
        this.add(assignmentNameInfo);
        this.add(assignmentGradeInfo);
        this.add(assignmentWeightInfo);
        this.add(assignmentDueDateInfo);
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
        } else if (evt.getSource() == deleteAssignment) {
            // Handle delete assignment button click
            int selectedRow = assignmentTable.getSelectedRow();
            if (selectedRow != -1) { // Ensure a row is selected
                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this assignment?",
                        "Delete Confirmation",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    tableModel.removeRow(selectedRow); // Remove the selected row
                    JOptionPane.showMessageDialog(this, "Assignment deleted successfully.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No assignment selected. Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        }


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

}
