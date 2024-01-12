package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestPage extends JFrame {
    private String studentName;
    private String rollNumber;
    private String testName;

    private JLabel studentInfoLabel;
    private JLabel questionLabel;
    private JRadioButton[] options;
    private JButton nextButton;
    private JButton previousButton;
    private JButton submitButton;

    private int currentQuestionIndex;
    private String[] questions;
    private String[][] optionsData;
    private ButtonGroup optionGroup;
    private String[] studentAnswers;
    private Timer timer;
    private int timeRemaining;

    public TestPage(String studentName, String rollNumber, String testName) {
        super(testName);

        this.studentName = studentName;
        this.rollNumber = rollNumber;
        this.testName = testName;

        // Initialize questions and options (replace with your actual data)
        initializeQuestionsAndOptions();

        studentInfoLabel = new JLabel("Student: " + studentName + " | Roll Number: " + rollNumber);
        questionLabel = new JLabel();
        options = new JRadioButton[4];
        nextButton = new JButton("Next");
        previousButton = new JButton("Previous");
        submitButton = new JButton("Submit");

        optionGroup = new ButtonGroup();
        for (int i = 0; i < options.length; i++) {
            options[i] = new JRadioButton();
            optionGroup.add(options[i]);
        }

        setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel();
        infoPanel.add(studentInfoLabel);

        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BorderLayout());
        questionPanel.add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1));
        for (int i = 0; i < options.length; i++) {
            optionsPanel.add(options[i]);
        }
        questionPanel.add(optionsPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(submitButton);

        add(infoPanel, BorderLayout.NORTH);
        add(questionPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Set initial question
        currentQuestionIndex = 0;
        setQuestion(currentQuestionIndex);

        // Initialize timer
        timeRemaining = 5 * 60; // 5 minutes
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTimer();
            }
        });
        timer.start();

        // Add action listeners
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAnswer();
                if (currentQuestionIndex < questions.length - 1) {
                    currentQuestionIndex++;
                    setQuestion(currentQuestionIndex);
                }
            }
        });

        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAnswer();
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex--;
                    setQuestion(currentQuestionIndex);
                }
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAnswer();
                submitTest();
            }
        });

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeQuestionsAndOptions() {
        // Replace with your actual questions and options data
        questions = new String[] {
                "What is the correct way to create a new empty String object in Java?",
                "In Java, which operator is used to concatenate two strings?",
                "What is the difference between StringBuilder and StringBuffer in Java?",
                "What does the following string do to the given string str1?\nString str2 = str1.intern();",
                "Which of the following methods of the String class is used to obtain a character at a specified index?",
        };

        optionsData = new String[][] {
                { "a. String str = new String();",
                        "b. String str = \" \";",
                        "c. String str = \"\";",
                        "d. String str = null;" },
                { "a. +",
                        "b. &",
                        "c. .concat()",
                        "d. .toString()" },
                { "a. StringBuilder is mutable, while StringBuffer is immutable.",
                        "b. StringBuilder is not thread-safe, while StringBuffer is thread-safe.",
                        "c. StringBuilder is synchronized, while StringBuffer is not synchronized.",
                        "d. There is no difference; they can be used interchangeably." },
                { "It returns the canonical representation of the string.",
                        "It converts the given string to lowercase.",
                        "It converts the given string to uppercase.",
                        "It has no effect on the given string." },
                { "a. charAt()",
                        "b. getChar()",
                        "c. indexOf()",
                        "d. lastIndexOf()" },
        };
    }

    private void setQuestion(int index) {
        questionLabel.setText((index + 1) + ". " + questions[index]);
        for (int i = 0; i < options.length; i++) {
            options[i].setText(optionsData[index][i]);
            options[i].setSelected(false); // Clear the selected option
        }
        loadAnswer();
    }

    private void saveAnswer() {
        if (studentAnswers == null) {
            studentAnswers = new String[questions.length];
        }
        for (int i = 0; i < options.length; i++) {
            if (options[i].isSelected()) {
                studentAnswers[currentQuestionIndex] = optionsData[currentQuestionIndex][i];
                break;
            }
        }
    }

    private void loadAnswer() {
        if (studentAnswers != null && studentAnswers[currentQuestionIndex] != null) {
            for (int i = 0; i < options.length; i++) {
                if (studentAnswers[currentQuestionIndex].equals(optionsData[currentQuestionIndex][i])) {
                    options[i].setSelected(true);
                    return; // Exit the loop once the answer is found
                }
            }
        }

        // If no answer is found, ensure no option is selected
        optionGroup.clearSelection();
    }

    private void submitTest() {
        timer.stop(); // Stop the timer
        int correctAnswers = calculateCorrectAnswers();
        int totalQuestions = questions.length;
        double percentage = ((double) correctAnswers / totalQuestions) * 100;

        // Display results
        String resultMessage = "Test submitted.\n";
        resultMessage += "Correct Answers: " + correctAnswers + "\n";
        resultMessage += "Total Questions: " + totalQuestions + "\n";
        resultMessage += "Percentage: " + String.format("%.2f", percentage) + "%";

        JOptionPane.showMessageDialog(this, resultMessage);
        dispose(); // Close the test page after submission
    }

    private int calculateCorrectAnswers() {
        if (studentAnswers == null) {
            return 0;
        }

        int correctCount = 0;
        for (int i = 0; i < studentAnswers.length; i++) {
            if (studentAnswers[i] != null && studentAnswers[i].equals(optionsData[i][0])) {
                correctCount++;
            }
        }
        return correctCount;
    }

    private void updateTimer() {
        if (timeRemaining > 0) {
            timeRemaining--;
            int minutes = timeRemaining / 60;
            int seconds = timeRemaining % 60;
            setTitle("Test Page | Time Remaining: " + String.format("%02d:%02d", minutes, seconds));
        } else {
            timer.stop();
            submitTest();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TestPage("John Doe", "12345", "Java Strings");
            }
        });
    }
}
