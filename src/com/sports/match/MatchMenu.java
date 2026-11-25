package com.sports.match;

import java.util.List;
import java.util.Scanner;

public class MatchMenu {

    private static MatchDAO dao = new MatchDAO();

    public static void showMenu(Scanner sc) {

        int choice = -1;

        while (choice != 7) {

            System.out.println("\n------ MATCH & RESULTS ------");
            System.out.println("1. Add Match");
            System.out.println("2. View All Matches");
            System.out.println("3. Search Match by ID");
            System.out.println("4. Update Match Details");
            System.out.println("5. Record Match Result (Winner)");
            System.out.println("6. Delete Match");
            System.out.println("7. Back to Main Menu");

            choice = readInt(sc, "Enter your choice (1-7): ");

            switch (choice) {
                case 1: addMatch(sc); break;
                case 2: viewAllMatches(); break;
                case 3: searchMatch(sc); break;
                case 4: updateMatch(sc); break;
                case 5: recordResult(sc); break;
                case 6: deleteMatch(sc); break;
                case 7:
                    System.out.println("Going back to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please choose 1-7.");
            }
        }
    }

    private static int readInt(Scanner sc, String message) {

        while (true) {
            System.out.print(message);

            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    private static void printHeader() {

        System.out.println(String.format(
            "%-5s %-8s %-8s %-12s %-22s %-10s",
            "ID", "Team 1", "Team 2", "Date", "Venue", "Winner"
        ));

        System.out.println("---------------------------------------------------------------------");
    }

    // CREATE
    private static void addMatch(Scanner sc) {

        int id = readInt(sc, "Enter Match ID: ");
        int t1 = readInt(sc, "Enter Team 1 ID (1-5): ");
        int t2 = readInt(sc, "Enter Team 2 ID (1-5): ");

        if (t1 == t2) {
            System.out.println("A team cannot play against itself!");
            return;
        }

        System.out.print("Enter Match Date (YYYY-MM-DD): ");
        String date = sc.nextLine().trim();

        System.out.print("Enter Venue: ");
        String venue = sc.nextLine().trim();

        if (dao.addMatch(new Match(id, t1, t2, date, venue, 0))) {
            System.out.println("Match added successfully! (Result: Pending)");
        } else {
            System.out.println("Could not add match. (Check ID, Team IDs and date format)");
        }
    }

    // READ - all
    private static void viewAllMatches() {

        List<Match> matches = dao.getAllMatches();

        if (matches.isEmpty()) {
            System.out.println("No matches found.");
            return;
        }

        printHeader();

        for (Match m : matches) {
            System.out.println(m);
        }
    }

    // READ - one
    private static void searchMatch(Scanner sc) {

        int id = readInt(sc, "Enter Match ID to search: ");
        Match m = dao.getMatchById(id);

        if (m == null) {
            System.out.println("Match with ID " + id + " not found!");
        } else {
            printHeader();
            System.out.println(m);
        }
    }

    // UPDATE - details
    private static void updateMatch(Scanner sc) {

        int id = readInt(sc, "Enter Match ID to update: ");
        Match m = dao.getMatchById(id);

        if (m == null) {
            System.out.println("Match with ID " + id + " not found!");
            return;
        }

        System.out.println("Current details: " + m);

        int t1 = readInt(sc, "Enter new Team 1 ID: ");
        int t2 = readInt(sc, "Enter new Team 2 ID: ");

        if (t1 == t2) {
            System.out.println("A team cannot play against itself!");
            return;
        }

        m.setTeam1Id(t1);
        m.setTeam2Id(t2);

        System.out.print("Enter new Date (YYYY-MM-DD): ");
        m.setMatchDate(sc.nextLine().trim());

        System.out.print("Enter new Venue: ");
        m.setVenue(sc.nextLine().trim());

        m.setWinnerId(0);

        if (dao.updateMatch(m)) {
            System.out.println("Match updated successfully!");
        } else {
            System.out.println("Could not update match.");
        }
    }

    // UPDATE - result
    private static void recordResult(Scanner sc) {

        int id = readInt(sc, "Enter Match ID: ");
        Match m = dao.getMatchById(id);

        if (m == null) {
            System.out.println("Match with ID " + id + " not found!");
            return;
        }

        System.out.println("Match: Team " + m.getTeam1Id()
                + " vs Team " + m.getTeam2Id());

        int winner = readInt(sc,
                "Enter Winner Team ID (" + m.getTeam1Id()
                + " or " + m.getTeam2Id() + "): ");

        if (winner != m.getTeam1Id() && winner != m.getTeam2Id()) {
            System.out.println("Winner must be one of the two teams playing!");
            return;
        }

        m.setWinnerId(winner);

        if (dao.updateMatch(m)) {
            System.out.println("Result recorded! Winner: Team " + winner);
        } else {
            System.out.println("Could not record result.");
        }
    }

    // DELETE
    private static void deleteMatch(Scanner sc) {

        int id = readInt(sc, "Enter Match ID to delete: ");
        Match m = dao.getMatchById(id);

        if (m == null) {
            System.out.println("Match with ID " + id + " not found!");
            return;
        }

        System.out.print("Are you sure you want to delete this match? (y/n): ");

        if (sc.nextLine().trim().equalsIgnoreCase("y")) {

            if (dao.deleteMatch(id)) {
                System.out.println("Match deleted successfully!");
            } else {
                System.out.println("Could not delete match.");
            }

        } else {
            System.out.println("Delete cancelled.");
        }
    }
}