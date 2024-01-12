package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLoginPage extends JFrame {

    public AdminLoginPage() {
        super("Admin Login");

        // Create components
        JLabel titleLabel = new JLabel("Admin Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

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

        // Add username and password fields
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.add(new JLabel("Username:"));
        inputPanel.add(usernameField);
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
                // Check admin credentials (for demonstration purposes, use hardcoded credentials)
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals("admin") && password.equals("admin123")) {
                    // Admin login successful
                    openAdminDashboard();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials. Try again.", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Set frame properties
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void openAdminDashboard() {
        // Open the admin dashboard (You can customize this part)
        JOptionPane.showMessageDialog(null, "Admin Login Successful!", "Welcome", JOptionPane.INFORMATION_MESSAGE);
        dispose(); // Close the login window
        // Close the main application window
        // You can open the admin dashboard or perform other actions here
    }
}
