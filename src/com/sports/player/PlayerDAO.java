package com.sports.player;

import com.sports.db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

// PlayerDAO - does all database work for players (CRUD using JDBC)
public class PlayerDAO {

    // CREATE - add a new player
    public boolean addPlayer(Player p) {
        String sql = "INSERT INTO players (id, name, age, sport, team_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return false;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, p.getId());
            ps.setString(2, p.getName());
            ps.setInt(3, p.getAge());
            ps.setString(4, p.getSport());
            if (p.getTeamId() == 0) {
                ps.setNull(5, Types.INTEGER);   // no team
            } else {
                ps.setInt(5, p.getTeamId());
            }
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }

    // READ - get all players (stored in an ArrayList)
    public List<Player> getAllPlayers() {
        List<Player> list = new ArrayList<>();
        String sql = "SELECT * FROM players";
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return list;
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Player(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("sport"),
                        rs.getInt("team_id")));
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return list;
    }

    // READ - find one player by ID (returns null if not found)
    public Player getPlayerById(int id) {
        String sql = "SELECT * FROM players WHERE id = ?";
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return null;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Player(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("sport"),
                        rs.getInt("team_id"));
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return null;
    }

    // UPDATE - change details of a player
    public boolean updatePlayer(Player p) {
        String sql = "UPDATE players SET name = ?, age = ?, sport = ?, team_id = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return false;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setInt(2, p.getAge());
            ps.setString(3, p.getSport());
            if (p.getTeamId() == 0) {
                ps.setNull(4, Types.INTEGER);
            } else {
                ps.setInt(4, p.getTeamId());
            }
            ps.setInt(5, p.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }

    // DELETE - remove a player by ID
    public boolean deletePlayer(int id) {
        String sql = "DELETE FROM players WHERE id = ?";
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