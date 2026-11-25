package com.sports.match;

import com.sports.db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

// MatchDAO - does all database work for matches (CRUD using JDBC)
public class MatchDAO {

    // CREATE - add a new match
    public boolean addMatch(Match m) {
        String sql = "INSERT INTO matches (id, team1_id, team2_id, match_date, venue, winner_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return false;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, m.getId());
            ps.setInt(2, m.getTeam1Id());
            ps.setInt(3, m.getTeam2Id());
            ps.setString(4, m.getMatchDate());
            ps.setString(5, m.getVenue());
            if (m.getWinnerId() == 0) {
                ps.setNull(6, Types.INTEGER);   // result not declared yet
            } else {
                ps.setInt(6, m.getWinnerId());
            }
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }

    // READ - get all matches (stored in an ArrayList)
    public List<Match> getAllMatches() {
        List<Match> list = new ArrayList<>();
        String sql = "SELECT * FROM matches";
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return list;
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Match(
                        rs.getInt("id"),
                        rs.getInt("team1_id"),
                        rs.getInt("team2_id"),
                        rs.getString("match_date"),
                        rs.getString("venue"),
                        rs.getInt("winner_id")));
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return list;
    }

    // READ - find one match by ID (returns null if not found)
    public Match getMatchById(int id) {
        String sql = "SELECT * FROM matches WHERE id = ?";
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return null;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Match(
                        rs.getInt("id"),
                        rs.getInt("team1_id"),
                        rs.getInt("team2_id"),
                        rs.getString("match_date"),
                        rs.getString("venue"),
                        rs.getInt("winner_id"));
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return null;
    }

    // UPDATE - change match details or result
    public boolean updateMatch(Match m) {
        String sql = "UPDATE matches SET team1_id = ?, team2_id = ?, match_date = ?, venue = ?, winner_id = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return false;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, m.getTeam1Id());
            ps.setInt(2, m.getTeam2Id());
            ps.setString(3, m.getMatchDate());
            ps.setString(4, m.getVenue());
            if (m.getWinnerId() == 0) {
                ps.setNull(5, Types.INTEGER);
            } else {
                ps.setInt(5, m.getWinnerId());
            }
            ps.setInt(6, m.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }

    // DELETE - remove a match by ID
    public boolean deleteMatch(int id) {
        String sql = "DELETE FROM matches WHERE id = ?";
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return false;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }
}