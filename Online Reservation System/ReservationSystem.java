import java.util.ArrayList;
import java.util.Scanner;

class User {
    private final String username;
    private final String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}

class Reservation {
    private final String username;
    private final int seatNumber;

    public Reservation(String username, int seatNumber) {
        this.username = username;
        this.seatNumber = seatNumber;
    }

    public String getUsername() {
        return username;
    }

    public int getSeatNumber() {
        return seatNumber;
    }
}

public class ReservationSystem {
    private final ArrayList<User> users;
    private final ArrayList<Reservation> reservations;
    private final boolean[] seats;

    public ReservationSystem(int numberOfSeats) {
        users = new ArrayList<>();
        reservations = new ArrayList<>();
        seats = new boolean[numberOfSeats];
    }

    public void registerUser(String username, String password) {
        users.add(new User(username, password));
    }

    public User loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.checkPassword(password)) {
                return user;
            }
        }
        return null;
    }

    public void showAvailableSeats() {
        System.out.println("Available Seats:");
        for (int i = 0; i < seats.length; i++) {
            if (!seats[i]) {
                System.out.print("Seat " + (i + 1) + " ");
            }
        }
        System.out.println();
    }

    public void makeReservation(User user, int seatNumber) {
        if (seatNumber > 0 && seatNumber <= seats.length && !seats[seatNumber - 1]) {
            seats[seatNumber - 1] = true;
            reservations.add(new Reservation(user.getUsername(), seatNumber));
            System.out.println("Reservation successful for seat " + seatNumber);
        } else {
            System.out.println("Seat is already booked or invalid seat number.");
        }
    }

    public void cancelReservation(User user, int seatNumber) {
        Reservation toCancel = null;
        for (Reservation res : reservations) {
            if (res.getUsername().equals(user.getUsername()) && res.getSeatNumber() == seatNumber) {
                toCancel = res;
                break;
            }
        }

        if (toCancel != null) {
            reservations.remove(toCancel);
            seats[seatNumber - 1] = false;
            System.out.println("Reservation for seat " + seatNumber + " canceled.");
        } else {
            System.out.println("No reservation found for the specified seat.");
        }
    }

    public void viewReservations(User user) {
        System.out.println("Reservations for " + user.getUsername() + ":");
        for (Reservation res : reservations) {
            if (res.getUsername().equals(user.getUsername())) {
                System.out.println("Seat " + res.getSeatNumber());
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReservationSystem system = new ReservationSystem(10);

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                system.registerUser(username, password);
                System.out.println("Registration successful!");

            } else if (choice == 2) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                User user = system.loginUser(username, password);

                if (user != null) {
                    System.out.println("Login successful!");
                    boolean loggedIn = true;

                    while (loggedIn) {
                        System.out.println("1. View available seats");
                        System.out.println("2. Make a reservation");
                        System.out.println("3. Cancel a reservation");
                        System.out.println("4. View your reservations");
                        System.out.println("5. Logout");
                        System.out.print("Choose an option: ");
                        int userChoice = scanner.nextInt();

                        switch (userChoice) {
                            case 1 -> system.showAvailableSeats();
                            case 2 -> {
                                System.out.print("Enter seat number to reserve: ");
                                int seatNumber = scanner.nextInt();
                                system.makeReservation(user, seatNumber);
                            }
                            case 3 -> {
                                System.out.print("Enter seat number to cancel: ");
                                int cancelSeatNumber = scanner.nextInt();
                                system.cancelReservation(user, cancelSeatNumber);
                            }
                            case 4 -> system.viewReservations(user);
                            case 5 -> {
                                loggedIn = false;
                                System.out.println("Logged out.");
                            }
                            default -> System.out.println("Invalid option. Please try again.");
                        }
                    }
                } else {
                    System.out.println("Invalid username or password.");
                }
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
