package dao;

import model.Revenue;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RevenueDAO {
    public static List<Revenue> statisticalBill(java.util.Date fromDate, java.util.Date toDate) {
        List<Revenue> list = new ArrayList<>();
        String sql = "SELECT DATE(DateCheckIn) as billDate, SUM(totalPrice) as totalRevenue FROM Bill " +
                     "WHERE DateCheckIn BETWEEN ? AND ? GROUP BY DATE(DateCheckIn)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(fromDate.getTime()));
            ps.setDate(2, new java.sql.Date(toDate.getTime()));
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Revenue revenue = new Revenue(rs.getDate("billDate"), rs.getFloat("totalRevenue"));
                    list.add(revenue);
                }
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
