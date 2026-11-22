package team;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// TeamDAO - does all database work for teams (CRUD using JDBC)
public class TeamDAO {

    // CREATE - add a new team
    public boolean addTeam(Team t) {
        String sql = "INSERT INTO teams (id, team_name, coach_name, sport) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return false;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, t.getId());
            ps.setString(2, t.getTeamName());
            ps.setString(3, t.getCoachName());
            ps.setString(4, t.getSport());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }

    // READ - get all teams (stored in an ArrayList)
    public List<Team> getAllTeams() {
        List<Team> list = new ArrayList<>();
        String sql = "SELECT * FROM teams";
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return list;
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Team(
                        rs.getInt("id"),
                        rs.getString("team_name"),
                        rs.getString("coach_name"),
                        rs.getString("sport")));
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return list;
    }

    // READ - find one team by ID (returns null if not found)
    public Team getTeamById(int id) {
        String sql = "SELECT * FROM teams WHERE id = ?";
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return null;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Team(
                        rs.getInt("id"),
                        rs.getString("team_name"),
                        rs.getString("coach_name"),
                        rs.getString("sport"));
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return null;
    }

    // READ - show players of a team (uses players table)
    public List<String> getPlayersOfTeam(int teamId) {
        List<String> names = new ArrayList<>();
        String sql = "SELECT name FROM players WHERE team_id = ?";
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return names;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, teamId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                names.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return names;
    }

    // UPDATE - change details of a team
    public boolean updateTeam(Team t) {
        String sql = "UPDATE teams SET team_name = ?, coach_name = ?, sport = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return false;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getTeamName());
            ps.setString(2, t.getCoachName());
            ps.setString(3, t.getSport());
            ps.setInt(4, t.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }

    // DELETE - remove a team by ID
    public boolean deleteTeam(int id) {
        String sql = "DELETE FROM teams WHERE id = ?";
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return false;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            // Happens when players or matches are still linked to this team
            System.out.println("Cannot delete: this team still has players or matches linked to it.");
            return false;
        }
    }
}