package com.sports.player;

import java.util.List;
import java.util.Scanner;

// PlayerMenu - shows Player Management menu and takes user input
public class PlayerMenu {

    private static PlayerDAO dao = new PlayerDAO();

    public static void showMenu(Scanner sc) {
        int choice = -1;

        while (choice != 6) {
            System.out.println("\n------ PLAYER MANAGEMENT ------");
            System.out.println("1. Add Player");
            System.out.println("2. View All Players");
            System.out.println("3. Search Player by ID");
            System.out.println("4. Update Player");
            System.out.println("5. Delete Player");
            System.out.println("6. Back to Main Menu");
            choice = readInt(sc, "Enter your choice (1-6): ");

            switch (choice) {
                case 1: addPlayer(sc); break;
                case 2: viewAllPlayers(); break;
                case 3: searchPlayer(sc); break;
                case 4: updatePlayer(sc); break;
                case 5: deletePlayer(sc); break;
                case 6: System.out.println("Going back to main menu..."); break;
                default: System.out.println("Invalid choice! Please choose 1-6.");
            }
        }
    }

    // Reads a number safely - keeps asking if user types letters (exception handling)
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

    // CREATE
    private static void addPlayer(Scanner sc) {
        int id = readInt(sc, "Enter Player ID: ");
        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();
        int age = readInt(sc, "Enter Age: ");
        System.out.print("Enter Sport: ");
        String sport = sc.nextLine().trim();
        int teamId = readInt(sc, "Enter Team ID (1-5, or 0 for no team): ");

        Player p = new Player(id, name, age, sport, teamId);
        if (dao.addPlayer(p)) {
            System.out.println("Player added successfully!");
        } else {
            System.out.println("Could not add player. (ID may already exist or Team ID is wrong)");
        }
    }

    // READ - all
    private static void viewAllPlayers() {
        List<Player> players = dao.getAllPlayers();
        if (players.isEmpty()) {
            System.out.println("No players found.");
            return;
        }
        System.out.println(String.format("%-5s %-20s %-5s %-12s %-5s", "ID", "Name", "Age", "Sport", "Team"));
        System.out.println("-----------------------------------------------------");
        for (Player p : players) {
            System.out.println(p);
        }
    }

    // READ - one
    private static void searchPlayer(Scanner sc) {
        int id = readInt(sc, "Enter Player ID to search: ");
        Player p = dao.getPlayerById(id);
        if (p == null) {
            System.out.println("Player with ID " + id + " not found!");
        } else {
            System.out.println(String.format("%-5s %-20s %-5s %-12s %-5s", "ID", "Name", "Age", "Sport", "Team"));
            System.out.println(p);
        }
    }

    // UPDATE
    private static void updatePlayer(Scanner sc) {
        int id = readInt(sc, "Enter Player ID to update: ");
        Player p = dao.getPlayerById(id);
        if (p == null) {
            System.out.println("Player with ID " + id + " not found!");
            return;
        }
        System.out.println("Current details: " + p);

        System.out.print("Enter new Name: ");
        p.setName(sc.nextLine().trim());
        p.setAge(readInt(sc, "Enter new Age: "));
        System.out.print("Enter new Sport: ");
        p.setSport(sc.nextLine().trim());
        p.setTeamId(readInt(sc, "Enter new Team ID (1-5, or 0 for no team): "));

        if (dao.updatePlayer(p)) {
            System.out.println("Player updated successfully!");
        } else {
            System.out.println("Could not update player.");
        }
    }

    // DELETE
    private static void deletePlayer(Scanner sc) {
        int id = readInt(sc, "Enter Player ID to delete: ");
        Player p = dao.getPlayerById(id);
        if (p == null) {
            System.out.println("Player with ID " + id + " not found!");
            return;
        }
        System.out.print("Are you sure you want to delete " + p.getName() + "? (y/n): ");
        String confirm = sc.nextLine().trim();
        if (confirm.equalsIgnoreCase("y")) {
            if (dao.deletePlayer(id)) {
                System.out.println("Player deleted successfully!");
            } else {
                System.out.println("Could not delete player.");
            }
        } else {
            System.out.println("Delete cancelled.");
        }
    }
}