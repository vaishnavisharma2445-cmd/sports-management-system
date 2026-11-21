package player;

// Player class - stores details of one player (OOP: encapsulation)
public class Player {

    // private fields - can only be accessed using getters/setters
    private int id;
    private String name;
    private int age;
    private String sport;
    private int teamId;

    // Constructor
    public Player(int id, String name, int age, String sport, int teamId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sport = sport;
        this.teamId = teamId;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getSport() { return sport; }
    public int getTeamId() { return teamId; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setSport(String sport) { this.sport = sport; }
    public void setTeamId(int teamId) { this.teamId = teamId; }

    // Prints player in one neat line
    @Override
    public String toString() {
        String team = (teamId == 0) ? "-" : String.valueOf(teamId);
        return String.format("%-5d %-20s %-5d %-12s %-5s", id, name, age, sport, team);
    }
}