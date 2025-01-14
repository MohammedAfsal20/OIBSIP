import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Random random = new Random();

            int targetNumber = random.nextInt(100) + 1;
            int numberOfTries = 0;
            boolean hasWon = false;

            System.out.println("Welcome to the Number Guessing Game!");
            System.out.println("I have selected a number between 1 and 100.");
            System.out.println("Can you guess what it is?");

            while (!hasWon) {
                System.out.print("Enter your guess: ");
                if (scanner.hasNextInt()) {
                    int guess = scanner.nextInt();
                    numberOfTries++;

                    if (guess < targetNumber) {
                        System.out.println("Too low! Try again.");
                    } else if (guess > targetNumber) {
                        System.out.println("Too high! Try again.");
                    } else {
                        hasWon = true;
                        System.out.println("Congratulations! You've guessed the correct number.");
                        System.out.println("It took you " + numberOfTries + " tries.");
                    }
                } else {
                    System.out.println("Please enter a valid number.");
                    scanner.next();
                }
            }
        }
    }
}
