import java.util.Scanner;

public class ATMInterface {
    private double balance;

    public ATMInterface(double initialBalance) {
        this.balance = initialBalance;
    }

    public void checkBalance() {
        System.out.println("Your current balance is: $" + balance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("You have successfully deposited $" + amount);
            checkBalance();
        } else {
            System.out.println("Invalid amount. Please enter a positive value.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("You have successfully withdrawn $" + amount);
            checkBalance();
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            System.out.println("Invalid amount. Please enter a positive value.");
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ATMInterface atm = new ATMInterface(500.00);
            int choice;

            do {
                System.out.println("\nWelcome to the ATM");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Exit");
                System.out.print("Please choose an option: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> atm.checkBalance();
                    case 2 -> {
                        System.out.print("Enter the amount to deposit: $");
                        double depositAmount = scanner.nextDouble();
                        atm.deposit(depositAmount);
                    }
                    case 3 -> {
                        System.out.print("Enter the amount to withdraw: $");
                        double withdrawAmount = scanner.nextDouble();
                        atm.withdraw(withdrawAmount);
                    }
                    case 4 -> System.out.println("Thank you for using the ATM. Goodbye!");
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 4);
        }
    }
}
