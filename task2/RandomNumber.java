import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RandomNumber {
    private static int score = 0;
    private static int chances = 5;
    private static int randomNumber;

    private static JLabel infoLabel, inLabel, chancesLabel, messageLabel, scoreLabel;
    private static JTextField inputField;
    private static JButton guessButton, quitButton, resetButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Number Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 250);
        frame.setLayout(null);

        infoLabel = new JLabel("Welcome to the Number Guessing Game.");
        infoLabel.setBounds(10, 5, 380, 50);
        inLabel = new JLabel("Guess a number between 1 and 100 in only 5 chances.");
        inLabel.setBounds(10, 30, 380, 50);
        frame.add(infoLabel);
        frame.add(inLabel);

        inputField = new JTextField();
        inputField.setBounds(20, 70, 100, 30);
        frame.add(inputField);

        guessButton = new JButton("Guess");
        guessButton.setBounds(130, 70, 80, 30);
        frame.add(guessButton);

        chancesLabel = new JLabel("Chances left: " + chances);
        chancesLabel.setBounds(220, 70, 120, 30);
        frame.add(chancesLabel);

        messageLabel = new JLabel("Hint: ?");
        messageLabel.setBounds(20, 100, 380, 30);
        frame.add(messageLabel);

        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setBounds(20, 120, 120, 30);
        frame.add(scoreLabel);

        quitButton = new JButton("Quit Game");
        quitButton.setBounds(20, 150, 120, 30);
        frame.add(quitButton);

        resetButton = new JButton("Reset Game");
        resetButton.setBounds(160, 150, 120, 30);
        frame.add(resetButton);

        frame.setVisible(true);

        randomNumber = generateRandomNumber();

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int number = Integer.parseInt(inputField.getText());

                if (number == randomNumber) {
                    messageLabel.setText("Hurray, You guessed the number! You won.");
                    score += (chances * 10);
                    scoreLabel.setText("Score: " + score);
                    guessButton.setEnabled(false);
                } else if (number <= 0 || number > 100) {
                    messageLabel.setText("Invalid guess! Enter a number between 1 and 100.");
                } else {
                    updateHints(number);
                    chances--;
                    chancesLabel.setText("Chances left: " + chances);
                    if (chances == 0) {
                        messageLabel.setText("You are out of chances... The number was: " + randomNumber);
                        guessButton.setEnabled(false);
                    }
                }
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
    }

    private static int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    private static void updateHints(int number) {
        if (number < randomNumber) {
            if ((randomNumber - number) < 10) {
                messageLabel.setText("Your number is small, but you are close.");
            } else {
                messageLabel.setText("Your number is too small.");
            }
        } else {
            if ((number - randomNumber) < 10) {
                messageLabel.setText("Your number is large, but you are close.");
            } else {
                messageLabel.setText("Your number is too large.");
            }
        }
    }

    private static void resetGame() {
        score = 0;
        chances = 5;
        scoreLabel.setText("Score: " + score);
        chancesLabel.setText("Chances left: " + chances);
        randomNumber = generateRandomNumber();
        messageLabel.setText("Hint: ?");
        guessButton.setEnabled(true);
    }
}
