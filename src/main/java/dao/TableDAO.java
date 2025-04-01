package dao;

import model.TableDrink;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableDAO {
    public static List<TableDrink> loadTableList() {
        List<TableDrink> list = new ArrayList<>();
        String sql = "SELECT * FROM TableDrink";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while(rs.next()){
                TableDrink table = new TableDrink(rs.getInt("id"), rs.getString("name"), rs.getString("status"));
                list.add(table);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    public static boolean switchTable(int idTable1, int idTable2) {
        // Giả lập chuyển bàn (thực tế cần cập nhật hóa đơn và trạng thái bàn)
        return true;
    }
    
    public static boolean editTable(TableDrink table) {
        String sql = "UPDATE TableDrink SET name = ?, status = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, table.getName());
            ps.setString(2, table.getStatus());
            ps.setInt(3, table.getId());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public static boolean deleteTable(int id) {
        String sql = "DELETE FROM TableDrink WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public static boolean addNewTable(TableDrink table) {
        String sql = "INSERT INTO TableDrink (name, status) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, table.getName());
            ps.setString(2, table.getStatus());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
