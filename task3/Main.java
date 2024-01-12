import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}

class Transaction {
    private String description;
    private double amount;

    public Transaction(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }
}

class User {
    private String userId;
    private String userPin;
    private double balance;
    private List<Transaction> transactionHistory;

    public User(String userId, String userPin, double balance) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = balance;
        this.transactionHistory = new LinkedList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPin() {
        return userPin;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add(new Transaction("Deposit", amount));
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", -amount));
            return true;
        } else {
            System.out.println("Insufficient funds!");
            return false;
        }
    }

    public boolean transfer(User recipient, double amount) {
        if (withdraw(amount)) {
            recipient.deposit(amount);
            transactionHistory.add(new Transaction("Transfer to " + recipient.getUserId(), -amount));
            recipient.transactionHistory.add(new Transaction("Transfer from " + getUserId(), amount));
            return true;
        } else {
            return false;
        }
    }
}

class ATM {
    private Map<String, User> users;
    private User currentUser;

    public ATM() {
        this.users = new HashMap<>();
        initializeUsers();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM!");
        System.out.print("Enter User ID: ");
        String userId = scanner.next();
        System.out.print("Enter User PIN: ");
        String userPin = scanner.next();

        User user = users.get(userId);

        if (user != null && user.getUserPin().equals(userPin)) {
            currentUser = user;
            showMenu();
        } else {
            System.out.println("Invalid credentials. Exiting...");
        }
    }

    private void initializeUsers() {
        users.put("123456", new User("123456", "1234", 1000));
        users.put("456789", new User("456789", "4567", 500));
    }

    private void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayTransactionHistory();
                    break;
                case 2:
                    System.out.print("Enter the amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (currentUser.withdraw(withdrawAmount)) {
                        System.out.println("Withdrawal successful. Remaining balance: " + currentUser.getBalance());
                    }
                    break;
                case 3:
                    System.out.print("Enter the amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    currentUser.deposit(depositAmount);
                    System.out.println("Deposit successful. New balance: " + currentUser.getBalance());
                    break;
                case 4:
                    System.out.print("Enter the recipient's User ID: ");
                    String recipientId = scanner.next();
                    User recipient = users.get(recipientId);
                    if (recipient != null) {
                        System.out.print("Enter the amount to transfer: ");
                        double transferAmount = scanner.nextDouble();
                        if (currentUser.transfer(recipient, transferAmount)) {
                            System.out.println("Transfer successful. Remaining balance: " + currentUser.getBalance());
                        } else {
                            System.out.println("Transfer failed. Insufficient funds.");
                        }
                    } else {
                        System.out.println("Recipient not found.");
                    }
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 5);
    }

    private void displayTransactionHistory() {
        System.out.println("\nTransaction History:");
        for (Transaction transaction : currentUser.getTransactionHistory()) {
            System.out.println(transaction.getDescription() + ": " + transaction.getAmount());
        }
    }
}
