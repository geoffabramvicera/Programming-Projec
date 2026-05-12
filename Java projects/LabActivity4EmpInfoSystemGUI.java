import java.awt.*;
import java.awt.event.*;

public class LabActivity4EmpInfoSystemGUI extends Frame{
    
    public LabActivity4EmpInfoSystemGUI() {

        setTitle("Laboratory Activity 4");
        setSize(500, 700);
        setLayout(null);
        setVisible(true);

        Label outputLabel = new Label("Output:");
        outputLabel.setBounds(10, 530, 100, 20);
        outputLabel.setFont(new Font("Arial",Font.PLAIN, 14));
        add(outputLabel);

        TextArea outputArea = new TextArea();
        outputArea.setBounds(7, 585, 487, 110);
        outputArea.setFont(new Font("Arial",Font.PLAIN, 14));
        outputArea.setEditable(false);
        add(outputArea);

        Label firstNameLabel = new Label("First Name");
        firstNameLabel.setBounds(10, 40, 100, 20);
        firstNameLabel.setFont(new Font("Arial",Font.PLAIN, 14));
        add(firstNameLabel);

        TextField firstNameField = new TextField();
        firstNameField.setBounds(260, 30, 350, 40);
        firstNameField.setFont(new Font("Arial",Font.PLAIN, 14));
        add(firstNameField);

        Label lastNameLabel = new Label("Last Name");
        lastNameLabel.setBounds(10, 80, 100, 20);
        lastNameLabel.setFont(new Font("Arial",Font.PLAIN, 14));
        add(lastNameLabel);

        TextField lastNameField = new TextField();
        lastNameField.setBounds(260, 70, 350, 40);
        lastNameField.setFont(new Font("Arial",Font.PLAIN, 14));
        add(lastNameField);

        Label ageLabel = new Label("Age");
        ageLabel.setBounds(10, 120, 100, 20);
        ageLabel.setFont(new Font("Arial",Font.PLAIN, 14));
        add(ageLabel);

        TextField ageField = new TextField();
        ageField.setBounds(260, 110, 350, 40);
        ageField.setFont(new Font("Arial",Font.PLAIN, 14));
        add(ageField);

        Label hoursWorkedLabel = new Label("Hours Worked");
        hoursWorkedLabel.setBounds(10, 160, 100, 20);
        hoursWorkedLabel.setFont(new Font("Arial",Font.PLAIN, 14));
        add(hoursWorkedLabel);

        TextField hoursWorkedField = new TextField();
        hoursWorkedField.setBounds(260, 150, 350, 40);
        hoursWorkedField.setFont(new Font("Arial",Font.PLAIN, 14));
        add(hoursWorkedField);

        Label hourlyRateLabel = new Label("Hourly Rate");
        hourlyRateLabel.setBounds(10, 200, 100, 20);
        hourlyRateLabel.setFont(new Font("Arial",Font.PLAIN, 14));
        add(hourlyRateLabel);

        TextField hourlyRateField = new TextField();
        hourlyRateField.setBounds(260, 190, 350, 40);
        hourlyRateField.setFont(new Font("Arial",Font.PLAIN, 14));
        add(hourlyRateField);

        Button submitButton = new Button("Submit");
        submitButton.setBounds(220, 240, 50, 20);
        add(submitButton);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (firstNameField.getText().isBlank() ||
                lastNameField.getText().isBlank() ||
                ageField.getText().isBlank() ||
                hoursWorkedField.getText().isBlank() ||
                hourlyRateField.getText().isBlank()) { 
                outputArea.append("Error: All fields must be filled out.\n");
                }
                
                try {
                    int age = Integer.parseInt(ageField.getText());
                 if (age < 0) {
                        outputArea.append("Error: Age can't be less than 0\n");
                    }
                } catch (NumberFormatException Invalid) {
                    outputArea.append("Error: Age must be a valid integer.\n");
                } 

                try {
                    double hours_worked = Double.parseDouble(hoursWorkedField.getText().trim());
                    double hourly_rate = Double.parseDouble(hourlyRateField.getText().trim());
                    if (hours_worked < 0) {
                        outputArea.append("Error: Hours worked can't be less than 0\n");
                    } else if (hourly_rate < 0) {
                        outputArea.append("Error: Hourly rate can't be less than 0\n");
                    }
                } catch (NumberFormatException Invalid) {
                    outputArea.append("Error: Hours worked and hourly rate must be valid numbers.\n");
                }

                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                int age = Integer.parseInt(ageField.getText());
                double hoursWorked = Double.parseDouble(hoursWorkedField.getText());
                double hourlyRate = Double.parseDouble(hourlyRateField.getText());

                outputArea.append("Full Name: ");
                outputArea.append(firstName + " " + lastName + "\n");
                outputArea.append("Age: " + age + " years old \n");
                outputArea.append("Daily Salary: " + hoursWorked * hourlyRate);

            }
        });
    }

    public static void main(String[] args) {
        new LabActivity4EmpInfoSystemGUI();

    }
}