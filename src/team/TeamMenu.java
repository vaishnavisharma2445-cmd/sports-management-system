package team;

import java.util.List;
import java.util.Scanner;

// TeamMenu - shows Team Management menu and takes user input
public class TeamMenu {

    private static TeamDAO dao = new TeamDAO();

    public static void showMenu(Scanner sc) {
        int choice = -1;

        while (choice != 7) {
            System.out.println("\n------ TEAM MANAGEMENT ------");
            System.out.println("1. Add Team");
            System.out.println("2. View All Teams");
            System.out.println("3. Search Team by ID");
            System.out.println("4. View Players of a Team");
            System.out.println("5. Update Team");
            System.out.println("6. Delete Team");
            System.out.println("7. Back to Main Menu");
            choice = readInt(sc, "Enter your choice (1-7): ");

            switch (choice) {
                case 1: addTeam(sc); break;
                case 2: viewAllTeams(); break;
                case 3: searchTeam(sc); break;
                case 4: viewPlayersOfTeam(sc); break;
                case 5: updateTeam(sc); break;
                case 6: deleteTeam(sc); break;
                case 7: System.out.println("Going back to main menu..."); break;
                default: System.out.println("Invalid choice! Please choose 1-7.");
            }
        }
    }

    // Reads a number safely (exception handling)
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
        System.out.println(String.format("%-5s %-22s %-20s %-12s", "ID", "Team Name", "Coach", "Sport"));
        System.out.println("-------------------------------------------------------------");
    }

    // CREATE
    private static void addTeam(Scanner sc) {
        int id = readInt(sc, "Enter Team ID: ");
        System.out.print("Enter Team Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter Coach Name: ");
        String coach = sc.nextLine().trim();
        System.out.print("Enter Sport: ");
        String sport = sc.nextLine().trim();

        if (dao.addTeam(new Team(id, name, coach, sport))) {
            System.out.println("Team added successfully!");
        } else {
            System.out.println("Could not add team. (ID may already exist)");
        }
    }

    // READ - all
    private static void viewAllTeams() {
        List<Team> teams = dao.getAllTeams();
        if (teams.isEmpty()) {
            System.out.println("No teams found.");
            return;
        }
        printHeader();
        for (Team t : teams) {
            System.out.println(t);
        }
    }

    // READ - one
    private static void searchTeam(Scanner sc) {
        int id = readInt(sc, "Enter Team ID to search: ");
        Team t = dao.getTeamById(id);
        if (t == null) {
            System.out.println("Team with ID " + id + " not found!");
        } else {
            printHeader();
            System.out.println(t);
        }
    }

    // READ - players of a team
    private static void viewPlayersOfTeam(Scanner sc) {
        int id = readInt(sc, "Enter Team ID: ");
        Team t = dao.getTeamById(id);
        if (t == null) {
            System.out.println("Team with ID " + id + " not found!");
            return;
        }
        List<String> players = dao.getPlayersOfTeam(id);
        System.out.println("Players in " + t.getTeamName() + ":");
        if (players.isEmpty()) {
            System.out.println("  No players in this team yet.");
        } else {
            for (String name : players) {
                System.out.println("  - " + name);
            }
        }
    }

    // UPDATE
    private static void updateTeam(Scanner sc) {
        int id = readInt(sc, "Enter Team ID to update: ");
        Team t = dao.getTeamById(id);
        if (t == null) {
            System.out.println("Team with ID " + id + " not found!");
            return;
        }
        System.out.println("Current details: " + t);

        System.out.print("Enter new Team Name: ");
        t.setTeamName(sc.nextLine().trim());
        System.out.print("Enter new Coach Name: ");
        t.setCoachName(sc.nextLine().trim());
        System.out.print("Enter new Sport: ");
        t.setSport(sc.nextLine().trim());

        if (dao.updateTeam(t)) {
            System.out.println("Team updated successfully!");
        } else {
            System.out.println("Could not update team.");
        }
    }

    // DELETE
    private static void deleteTeam(Scanner sc) {
        int id = readInt(sc, "Enter Team ID to delete: ");
        Team t = dao.getTeamById(id);
        if (t == null) {
            System.out.println("Team with ID " + id + " not found!");
            return;
        }
        System.out.print("Are you sure you want to delete " + t.getTeamName() + "? (y/n): ");
        if (sc.nextLine().trim().equalsIgnoreCase("y")) {
            if (dao.deleteTeam(id)) {
                System.out.println("Team deleted successfully!");
            }
        } else {
            System.out.println("Delete cancelled.");
        }
    }
}