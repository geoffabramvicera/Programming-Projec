import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class LabActivity6SwingToDoList implements ActionListener {

    private JFrame viewerFrame;
    private JTable taskTable;
    private DefaultTableModel tableModel;
    private JButton addTaskButton;

    private JFrame formFrame;
    private JTextField taskNameField;
    private JTextArea taskDescriptionArea;
    private JComboBox<String> statusComboBox;
    private JButton saveButton;

    private boolean isFormOpen = false;

    public LabActivity6SwingToDoList() {
        initializeViewer();
    }
    private void initializeViewer() {
        viewerFrame = new JFrame("To-Do List Viewer");
        viewerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewerFrame.setSize(600, 400);
        viewerFrame.setLocationRelativeTo(null);
        viewerFrame.setLayout(new BorderLayout(10, 10));

        addTaskButton = new JButton("Add Task");
        addTaskButton.addActionListener(this);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(addTaskButton);

        viewerFrame.add(topPanel, BorderLayout.NORTH);

        String[] columns = {"Task Name", "Task Description", "Status"};

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        taskTable = new JTable(tableModel);
        taskTable.setFillsViewportHeight(true);
        taskTable.getColumnModel().getColumn(1).setPreferredWidth(300); 

        JScrollPane scrollPane = new JScrollPane(taskTable);

        viewerFrame.add(scrollPane, BorderLayout.CENTER);

        viewerFrame.setVisible(true);
    }

    private void initializeForm() {
        formFrame = new JFrame("Add New Task");
        formFrame.setLocationRelativeTo(viewerFrame);
        formFrame.setLayout(new BorderLayout(0, 0));
        formFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        formFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                isFormOpen = false;
            }
        });

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setPreferredSize(new Dimension(432, 200));
        GridBagConstraints gbc = new GridBagConstraints();

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

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nameLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(taskNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        formPanel.add(descriptionLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;  
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        formPanel.add(descriptionScroll, gbc);

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

        saveButton = new JButton("Save Task");
        saveButton.addActionListener(this);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(saveButton);
        formFrame.add(buttonPanel, BorderLayout.SOUTH);

        formFrame.pack(); 
        formFrame.setLocationRelativeTo(viewerFrame);
        formFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == addTaskButton) {
            if (!isFormOpen) {
                initializeForm();
                isFormOpen = true;
            } else {
                formFrame.toFront();
                formFrame.requestFocus();
            }
        }

        else if (source == saveButton) {
            saveTask();
        }
    }

    private void saveTask() {
        String taskName = taskNameField.getText().trim();
        String taskDescription = taskDescriptionArea.getText().trim();
        String status = (String) statusComboBox.getSelectedItem();

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

        tableModel.addRow(new Object[]{taskName, taskDescription, status});

        formFrame.dispose();
        isFormOpen = false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LabActivity6SwingToDoList());
    }
}