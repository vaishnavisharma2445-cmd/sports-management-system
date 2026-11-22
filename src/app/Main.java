package app;

import java.util.Scanner;
import player.PlayerMenu;
import team.TeamMenu;

// Main class - Entry point for the Sports Management System
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        // Loop until the user chooses to exit (Option 4)
        while (choice != 4) {
            System.out.println("\n==================================");
            System.out.println("    SPORTS MANAGEMENT SYSTEM      ");
            System.out.println("==================================");
            System.out.println("1. Player Management");
            System.out.println("2. Team Management");
            System.out.println("3. Match & Results");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");

            String input = sc.nextLine().trim();

            // Try-catch block to handle invalid non-numeric inputs (like letters)
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number (1-4).");
                continue;
            }

            // Menu options
            switch (choice) {
                case 1:
                    PlayerMenu.showMenu(sc);
                    break;
                case 2:
                    TeamMenu.showMenu(sc);
                    break;
                case 3:
                    System.out.println("\n[Match & Results] - Coming soon!");
                    break;
                case 4:
                    System.out.println("\nExiting Sports Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please choose an option between 1 and 4.");
            }
        }

        sc.close();
    }
}