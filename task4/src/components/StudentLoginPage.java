package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentLoginPage extends JFrame {


    public StudentLoginPage() {
        super("Student Login");
        // Create components
        JLabel titleLabel = new JLabel("Student Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JTextField nameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JTextField rollNumberField = new JTextField(20);

        JButton loginButton = new JButton("Login");

        // Set layout to GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.2;
        gbc.anchor = GridBagConstraints.CENTER;

        // Add title label to the center
        add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.2;

        // Add name and roll number fields
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Roll Number:"));
        inputPanel.add(rollNumberField);
        inputPanel.add(new JLabel("Password:"));
        inputPanel.add(passwordField);

        add(inputPanel, gbc);

        gbc.gridy = 2;
        gbc.weighty = 0.2;

        // Add login button
        add(loginButton, gbc);

        // Add action listener for login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // For demonstration purposes, no authentication is performed for student login
                // You can add your own logic for student authentication here

                // Student login successful
                openStudentDashboard(nameField.getText(), rollNumberField.getText());
            }
        });

        // Set frame properties
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void openStudentDashboard(String name, String rollNumber) {
        // Open the student dashboard (You can customize this part)
        Student student = new Student(name, rollNumber);
        JOptionPane.showMessageDialog(null,
                "Student Login Successfull!\nName: " + name + "\nRoll Number: " + rollNumber, "Welcome",
                JOptionPane.INFORMATION_MESSAGE);
        dispose(); // Close the login window
        // Close the main application window
        // You can open the student dashboard or perform other actions here
    }
}
