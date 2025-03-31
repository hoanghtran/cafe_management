package dao;

import model.TableDrink;
import util.DatabaseConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableDAO {

    public List<TableDrink> getAllTables() {
        List<TableDrink> tables = new ArrayList<>();
        String sql = "SELECT * FROM TableDrink";
        try (Connection conn = DatabaseConnector.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                TableDrink table = new TableDrink();
                table.setId(rs.getInt("id"));
                table.setName(rs.getString("name"));
                table.setStatus(rs.getString("status"));
                tables.add(table);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tables;
    }

    public boolean addTable(String tableName) {
        String sql = "INSERT INTO TableDrink(name, status) VALUES(?, 'Trống')";
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tableName);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean editTable(int id, String newName) {
        String sql = "UPDATE TableDrink SET name = ? WHERE id = ?";
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newName);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteTable(int id) {
        String sql = "DELETE FROM tables WHERE id = ?";
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateTableStatus(int id, String newStatus) {
        String sql = "UPDATE tables SET status = ? WHERE id = ?";
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newStatus);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean switchTable(int fromTableId, int toTableId) {
        String sql = "UPDATE orders SET table_id = ? WHERE table_id = ?";
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, toTableId);
            stmt.setInt(2, fromTableId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
