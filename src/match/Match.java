package match;

// Match class - stores details of one match (OOP: encapsulation)
public class Match {

    private int id;
    private int team1Id;
    private int team2Id;
    private String matchDate;   // format: YYYY-MM-DD
    private String venue;
    private int winnerId;       // 0 means result not declared yet

    // Constructor
    public Match(int id, int team1Id, int team2Id, String matchDate, String venue, int winnerId) {
        this.id = id;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.matchDate = matchDate;
        this.venue = venue;
        this.winnerId = winnerId;
    }

    // Getters
    public int getId() { return id; }
    public int getTeam1Id() { return team1Id; }
    public int getTeam2Id() { return team2Id; }
    public String getMatchDate() { return matchDate; }
    public String getVenue() { return venue; }
    public int getWinnerId() { return winnerId; }

    // Setters
    public void setTeam1Id(int team1Id) { this.team1Id = team1Id; }
    public void setTeam2Id(int team2Id) { this.team2Id = team2Id; }
    public void setMatchDate(String matchDate) { this.matchDate = matchDate; }
    public void setVenue(String venue) { this.venue = venue; }
    public void setWinnerId(int winnerId) { this.winnerId = winnerId; }

    // Prints match in one neat line
    @Override
    public String toString() {
        String winner = (winnerId == 0) ? "Pending" : "Team " + winnerId;
        return String.format("%-5d %-8s %-8s %-12s %-22s %-10s",
                id, "Team " + team1Id, "Team " + team2Id, matchDate, venue, winner);
    }
}