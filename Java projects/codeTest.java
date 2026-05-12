import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class codeTest implements ActionListener {

    // Main viewer window components
    private JFrame viewerFrame;
    private JTable taskTable;
    private DefaultTableModel tableModel;
    private JButton addTaskButton;

    // Form window components
    private JFrame formFrame;
    private JTextField taskNameField;
    private JTextArea taskDescriptionArea;
    private JComboBox<String> statusComboBox;
    private JButton saveButton;

    // Flag to check if form window is open
    private boolean isFormOpen = false;

    public codeTest() {
        initializeViewer();
    }

    // Initialize the To-Do List Viewer window
    private void initializeViewer() {
        viewerFrame = new JFrame("To-Do List Viewer");
        viewerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewerFrame.setSize(600, 400);
        viewerFrame.setLocationRelativeTo(null); // Center on screen
        viewerFrame.setLayout(new BorderLayout(10, 10));

        // Create the Add Task button and add ActionListener
        addTaskButton = new JButton("Add Task");
        addTaskButton.addActionListener(this);

        // Panel for the button at the top
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(addTaskButton);

        viewerFrame.add(topPanel, BorderLayout.NORTH);

        // Table columns
        String[] columns = {"Task Name", "Task Description", "Status"};

        // Initialize table model with columns and 0 rows
        tableModel = new DefaultTableModel(columns, 0) {
            // Make cells non-editable by overriding isCellEditable
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Initialize JTable with the model
        taskTable = new JTable(tableModel);
        taskTable.setFillsViewportHeight(true);
        taskTable.getColumnModel().getColumn(1).setPreferredWidth(300); // Wider description column

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(taskTable);

        viewerFrame.add(scrollPane, BorderLayout.CENTER);

        viewerFrame.setVisible(true);
    }

    // Initialize the To-Do List Form window
    private void initializeForm() {
        formFrame = new JFrame("Add New Task");
        formFrame.setLocationRelativeTo(viewerFrame); // Center relative to viewer
        formFrame.setLayout(new BorderLayout(0, 0));
        formFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // When form window closes, reset the flag
        formFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                isFormOpen = false;
            }
        });

        // Panel for form fields with GridBagLayout for good alignment
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setPreferredSize(new Dimension(432, 200));
        GridBagConstraints gbc = new GridBagConstraints();

        // Labels and inputs
        JLabel nameLabel = new JLabel("Task Name:");
        taskNameField = new JTextField(20);

        JLabel descriptionLabel = new JLabel("Task Description:");
        taskDescriptionArea = new JTextArea(5, 20);
        taskDescriptionArea.setLineWrap(true);
        taskDescriptionArea.setWrapStyleWord(true);
        JScrollPane descriptionScroll = new JScrollPane(taskDescriptionArea);
        descriptionScroll.setPreferredSize(new Dimension(300, 100));

        JLabel statusLabel = new JLabel("Status:");
        String[] statuses = {"Not Started", "Ongoing", "Completed"};
        statusComboBox = new JComboBox<>(statuses);

        // Layout constraints and adding components
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Task Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nameLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(taskNameField, gbc);

        // Task Description
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        formPanel.add(descriptionLabel, gbc);

        // Task Description Text Area
        gbc.gridx = 0;
        gbc.gridy = 3;  
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        formPanel.add(descriptionScroll, gbc);

        // Status
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;
        gbc.weighty = 0;

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(statusLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(statusComboBox, gbc);

        JPanel wrapperPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        wrapperPanel.add(formPanel);
        wrapperPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formFrame.add(wrapperPanel, BorderLayout.CENTER);

        // Save button at bottom
        saveButton = new JButton("Save Task");
        saveButton.addActionListener(this);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(saveButton);
        formFrame.add(buttonPanel, BorderLayout.SOUTH);

        formFrame.pack(); 
        formFrame.setLocationRelativeTo(viewerFrame);
        formFrame.setVisible(true);
    }

    // Handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        // If Add Task button clicked
        if (source == addTaskButton) {
            if (!isFormOpen) {
                initializeForm();
                isFormOpen = true;
            } else {
                // If form already open, bring it to front
                formFrame.toFront();
                formFrame.requestFocus();
            }
        }

        // If Save button clicked
        else if (source == saveButton) {
            saveTask();
        }
    }

    // Save task from form to table
    private void saveTask() {
        String taskName = taskNameField.getText().trim();
        String taskDescription = taskDescriptionArea.getText().trim();
        String status = (String) statusComboBox.getSelectedItem();

        // Simple validation: Task Name must not be empty
        if (taskName.isEmpty()) {
            JOptionPane optionPane = new JOptionPane(
                "Please fill in the Task Name and Status",
                JOptionPane.ERROR_MESSAGE
            );
            JDialog dialog = optionPane.createDialog(formFrame, "Input Error");
            dialog.setSize(330, 150);
            dialog.setLocationRelativeTo(formFrame);
            dialog.setVisible(true);
            return;
        }

        // Add new row to table model
        tableModel.addRow(new Object[]{taskName, taskDescription, status});

        // Close form window and reset flag
        formFrame.dispose();
        isFormOpen = false;
    }

    // Main method to run the application
    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater to ensure thread safety
        SwingUtilities.invokeLater(() -> new codeTest());
    }
}