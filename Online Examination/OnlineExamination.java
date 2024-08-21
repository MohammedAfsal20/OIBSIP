import java.util.Scanner;

class OnlineExamination {

    private final String[] questions = {
            "1. What is the capital of France?\n(a) Berlin\n(b) Madrid\n(c) Paris\n(d) Rome",
            "2. What is 5 + 7?\n(a) 10\n(b) 11\n(c) 12\n(d) 13",
            "3. Which is the smallest planet in our solar system?\n(a) Earth\n(b) Mars\n(c) Mercury\n(d) Venus",
            "4. What is the boiling point of water?\n(a) 90°C\n(b) 100°C\n(c) 120°C\n(d) 150°C",
            "5. What is the chemical symbol for water?\n(a) O\n(b) H2O\n(c) CO2\n(d) HO",
            "6. Who wrote 'Hamlet'?\n(a) Charles Dickens\n(b) William Shakespeare\n(c) Mark Twain\n(d) J.K. Rowling",
            "7. What is the square root of 64?\n(a) 6\n(b) 7\n(c) 8\n(d) 9",
            "8. Who is known as the 'Father of Computers'?\n(a) Alan Turing\n(b) Charles Babbage\n(c) Thomas Edison\n(d) Nikola Tesla",
            "9. What is the chemical symbol for gold?\n(a) Au\n(b) Ag\n(c) Fe\n(d) Pb",
            "10. What is the largest ocean on Earth?\n(a) Atlantic Ocean\n(b) Indian Ocean\n(c) Arctic Ocean\n(d) Pacific Ocean"
    };

    private final char[] answers = { 'c', 'c', 'c', 'b', 'b', 'b', 'c', 'b', 'a', 'd' };
    private final char[] userAnswers = new char[10];

    public void login() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (username.equals("user") && password.equals("pass")) {
            System.out.println("Login successful!\n");
            startExam();
        } else {
            System.out.println("Login failed. Try again.");
        }
    }

    public void startExam() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the Online Examination\n");

        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            System.out.print("Your answer: ");
            userAnswers[i] = sc.next().charAt(0);
        }

        calculateResult();
    }

    public void calculateResult() {
        int score = 0;
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == userAnswers[i]) {
                score++;
            }
        }

        System.out.println("\nYour score: " + score + "/" + answers.length);

        if (score >= 6) {
            System.out.println("Result: Pass");
        } else {
            System.out.println("Result: Fail");
        }
    }

    public static void main(String[] args) {
        OnlineExamination exam = new OnlineExamination();
        exam.login();
    }
}
