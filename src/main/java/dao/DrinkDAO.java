package dao;

import model.Drink;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DrinkDAO {
    public static List<Drink> getFoodByCategoryId(int idCategory) {
        List<Drink> list = new ArrayList<>();
        String sql = "SELECT * FROM Drink WHERE idCategory = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCategory);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Drink d = new Drink(rs.getInt("id"), rs.getString("name"), rs.getInt("idCategory"), rs.getFloat("price"));
                    list.add(d);
                }
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    public static List<Drink> getListFood() {
        List<Drink> list = new ArrayList<>();
        String sql = "SELECT * FROM Drink";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while(rs.next()){
                Drink d = new Drink(rs.getInt("id"), rs.getString("name"), rs.getInt("idCategory"), rs.getFloat("price"));
                list.add(d);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    public static List<Drink> searchFoodByName(String name) {
        List<Drink> list = new ArrayList<>();
        String sql = "SELECT * FROM Drink WHERE name LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Drink d = new Drink(rs.getInt("id"), rs.getString("name"), rs.getInt("idCategory"), rs.getFloat("price"));
                    list.add(d);
                }
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    public static boolean insertFood(Drink drink) {
        String sql = "INSERT INTO Drink (name, idCategory, price) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, drink.getName());
            ps.setInt(2, drink.getIdCategory());
            ps.setFloat(3, drink.getPrice());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public static boolean updateFood(Drink drink) {
        String sql = "UPDATE Drink SET name = ?, idCategory = ?, price = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, drink.getName());
            ps.setInt(2, drink.getIdCategory());
            ps.setFloat(3, drink.getPrice());
            ps.setInt(4, drink.getId());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public static boolean deleteFood(int id) {
        String sql = "DELETE FROM Drink WHERE id = ?";
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
}
