# Java ATM Program

A simple console-based ATM program in Java with user authentication and basic transaction functionalities.

## Prerequisites
Make sure you have Java Development Kit (JDK) installed on your machine. You can download it [here](https://www.oracle.com/java/technologies/javase-downloads.html).

## Getting Started
1. Clone this repository to your local machine:

    ```bash
    git clone https://github.com/<YOUR_GITHUB_USERNAME>/Java-ATM.git
    ```

2. Navigate to the project directory:

    ```bash
    cd Java-ATM
    ```

3. Compile the Java file:

    ```bash
    javac Main.java
    ```

4. Run the program:

    ```bash
    java Main
    ```

## Usage
1. Enter your User ID and User PIN when prompted.
2. Follow the on-screen menu to perform various ATM operations:
    - Option 1: View Transaction History
    - Option 2: Withdraw
    - Option 3: Deposit
    - Option 4: Transfer
    - Option 5: Quit

3. After selecting an option, follow the instructions and input required values.

## Example
```bash
Welcome to the ATM!
Enter User ID: 123456
Enter User PIN: 1234

ATM Menu:
1. Transactions History
2. Withdraw
3. Deposit
4. Transfer
5. Quit

Enter your choice: 1

Transaction History:
Deposit: 1000.0
Withdrawal: -200.0
Transfer to 789012: -100.0

ATM Menu:
1. Transactions History
2. Withdraw
3. Deposit
4. Transfer
5. Quit

Enter your choice: 5

Thank you for using the ATM. Goodbye!
