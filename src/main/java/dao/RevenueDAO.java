package dao;

import model.Revenue;
import util.DatabaseConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RevenueDAO {
    public List<Revenue> getListBillInfo(String date) {
        List<Revenue> list = new ArrayList<>();
        String sql = "SELECT * FROM Bill WHERE DATE(checkIn) = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, date);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Revenue rev = new Revenue(date, rs.getFloat("totalPrice"));
                list.add(rev);
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return list;
    }
    
    public List<Revenue> getDateBill(String date) {
        return getListBillInfo(date);
    }
    
    public List<Revenue> statisticalBill(String fromDate, String toDate) {
        List<Revenue> list = new ArrayList<>();
        String sql = "SELECT DATE(checkIn) as date, SUM(totalPrice) as total FROM Bill WHERE DATE(checkIn) BETWEEN ? AND ? GROUP BY DATE(checkIn)";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fromDate);
            pstmt.setString(2, toDate);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Revenue rev = new Revenue(rs.getString("date"), rs.getFloat("total"));
                list.add(rev);
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return list;
    }
}
