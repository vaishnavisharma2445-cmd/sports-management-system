package com.sports.team;

// Team class - stores details of one team (OOP: encapsulation)
public class Team {

    private int id;
    private String teamName;
    private String coachName;
    private String sport;

    // Constructor
    public Team(int id, String teamName, String coachName, String sport) {
        this.id = id;
        this.teamName = teamName;
        this.coachName = coachName;
        this.sport = sport;
    }

    // Getters
    public int getId() { return id; }
    public String getTeamName() { return teamName; }
    public String getCoachName() { return coachName; }
    public String getSport() { return sport; }

    // Setters
    public void setTeamName(String teamName) { this.teamName = teamName; }
    public void setCoachName(String coachName) { this.coachName = coachName; }
    public void setSport(String sport) { this.sport = sport; }

    // Prints team in one neat line
    @Override
    public String toString() {
        return String.format("%-5d %-22s %-20s %-12s", id, teamName, coachName, sport);
    }
}