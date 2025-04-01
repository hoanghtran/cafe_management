package dao;

import model.Category;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    public static List<Category> getListCategory() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM DrinkCategory ORDER BY id ASC;";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while(rs.next()){
                Category cat = new Category(rs.getInt("id"), rs.getString("name"));
                list.add(cat);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    public static Category getCategory(int id) {
        String sql = "SELECT * FROM DrinkCategory WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return new Category(rs.getInt("id"), rs.getString("name"));
                }
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static boolean addNewCategory(Category cat) {
        String sql = "INSERT INTO DrinkCategory (name) VALUES (?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cat.getName());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public static boolean deleteCategory(int id) {
        String sql = "DELETE FROM DrinkCategory WHERE id = ?";
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
    
    public static boolean editCategory(Category cat) {
        String sql = "UPDATE DrinkCategory SET name = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cat.getName());
            ps.setInt(2, cat.getId());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
