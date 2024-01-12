import components.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainApplication extends JFrame {
    private boolean isLoggedIn = false;

    public MainApplication() {
        super("Quiz Application");

        // JLabel for the title
        JLabel titleLabel = new JLabel("Online Examination");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Components
        JButton loginAdminButton = createStyledButton("Login as Admin");
        JButton loginStudentButton = createStyledButton("Login as Student");

        JPanel testsPanel = new JPanel(new GridLayout(3, 1));
        testsPanel
                .add(createStyledTestPanel("1) Java String", "Description: This tests your Knowledge on Java Strings"));

        testsPanel.add(createStyledTestPanel("2) Java Data Types",
                "Description: This test contains basic questions on Data Types"));

        testsPanel.add(createStyledTestPanel("3) Java Loops", "Description: You will have questions on for"));

        JButton closeButton = createStyledButton("Close");

        // Set layout to GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Horizontal weight
        gbc.weighty = 0.2; // Vertical weight
        gbc.anchor = GridBagConstraints.CENTER;

        // Add the title label to the center of the frame
        add(titleLabel, gbc);

        // Set layout to GridBagLayout for the rest of the components
        gbc.gridy = 1;
        // Adjust vertical weight for the components below the title
        gbc.weighty = 0.8;

        JPanel topPanel = new JPanel();
        topPanel.add(loginAdminButton);
        topPanel.add(loginStudentButton);

        add(topPanel, gbc);

        gbc.gridy = 2;
        add(testsPanel, gbc);

        gbc.gridy = 3;
        add(closeButton, gbc);

        // Add action listeners
        loginAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open admin login page
                openAdminLoginPage();
                loginAdminButton.setVisible(false);
                loginStudentButton.setVisible(false);
            }
        });

        loginStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open student login page
                openStudentLoginPage();
                loginStudentButton.setVisible(false);
                loginAdminButton.setVisible(false);
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the application
                System.exit(0);
            }
        });

        // Set frame properties
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(33, 150, 243)); // Blue color
        button.setFocusPainted(false); // Remove focus border
        return button;
    }

    private JPanel createStyledTestPanel(String testName, String testDescription) {
        JPanel testPanel = new JPanel(new BorderLayout());
        JLabel nameLabel = new JLabel(testName);
        JLabel descriptionLabel = new JLabel(testDescription);
        JButton takeTestButton = createStyledButton("Take Test");

        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.add(nameLabel);
        infoPanel.add(descriptionLabel);

        testPanel.setBackground(new Color(244, 244, 244)); // Light gray background
        testPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        takeTestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if the user is logged in
                if (isLoggedIn) {
                    // Check if the test is uploaded
                    if (testName.equals("1) Java String")) {
                        // Open the test page for the student
                        openTestPage(Student.name, Student.rollno, "Java Strings");
                    } else {
                        // Display a message if the test is not uploaded
                        JOptionPane.showMessageDialog(MainApplication.this, "Test not yet uploaded by admin.");
                    }
                } else {
                    // Display a message if the user is not logged in
                    JOptionPane.showMessageDialog(MainApplication.this, "You cannot take the test without logging in.");
                }
            }
        });

        testPanel.add(infoPanel, BorderLayout.CENTER);
        testPanel.add(takeTestButton, BorderLayout.EAST);

        return testPanel;
    }

    private void openAdminLoginPage() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdminLoginPage(); // Pass MainApplication reference
                setLoggedIn(true);
            }
        });
    }

    private void openStudentLoginPage() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentLoginPage(); // Pass MainApplication reference
                setLoggedIn(true);
            }
        });
    }

    private void openTestPage(String studentName, String rollNumber, String testName) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TestPage(studentName, rollNumber, testName);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainApplication();
            }
        });
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}
