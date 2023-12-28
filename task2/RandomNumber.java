import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class RandomNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        int guess = r.nextInt(101);
        ArrayList<Integer> numbers = new ArrayList<>(5);
        int chances = 5; // Player is given only 5 chances to guess the number
        int number;
        String Rules = "Welcome to the Random number guessing game\nThis game is to guess the number between 1 to 100 \nYou have only 5 chances to guess the number\nThe guessed number is mentioned too large/too small if difference between original number and guessed number is greater than 10\nThe guessed number is mentioned large/small if difference between original number and guessed number is greater than 10\nIf you enter a number less than or equal to 0 or greater than 100, your turn will be wasted";
        System.out.println(Rules);
        do {
            System.out.print("\nEnter the guess number: ");
            number = sc.nextInt();
            numbers.add(number);
            if (number == guess) {
                System.out.println("Hurray, You won the game");
                break;
            }
            if (number < guess) {
                if ((guess - number) < 10) {
                    System.out.println("Your number is small,but you are close");
                } else {
                    System.out.println("Your number is too small");
                }
            }
            if (number > guess) {
                if ((number - guess) < 10) {
                    System.out.println("Your number is large,but you are close");
                } else {
                    System.out.println("Your number is too large");
                }
            }
            chances--;
            if (chances == 0) {
                System.out.println("You are out of chances...Better luck next time");
                System.out.println("The number is " + guess);
            } else if (chances == 1) {
                System.out.println("You are left with only 1 chance");
            } else {
                System.out.println("You are left with only " + chances + " chances");
            }
            System.out.println("Entered numbers till now: " + numbers);
        } while (chances > 0);
        sc.close();
    }
}
