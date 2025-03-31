package dao;

import model.Drink;
import util.DatabaseConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DrinkDAO {
    public List<Drink> getAllDrinks() {
        List<Drink> foods = new ArrayList<>();
        String sql = "SELECT * FROM Drink";
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Drink food = new Drink(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("idCategory"),
                    rs.getFloat("price")
                );
                foods.add(food);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foods;
    }
    
    public boolean addDrink(Drink food) {
        String sql = "INSERT INTO Drink(name, idCategory, price) VALUES(?,?,?)";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, food.getName());
            pstmt.setInt(2, food.getCategoryId());
            pstmt.setFloat(3, food.getPrice());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
