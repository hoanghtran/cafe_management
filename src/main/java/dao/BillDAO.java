package dao;

import model.Bill;
import util.DatabaseConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    public Bill getCurrentBill(int tableId) {
        String sql = "SELECT * FROM Bill WHERE tableId = ? AND checkOut IS NULL";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, tableId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setIdTable(rs.getInt("idTable"));
                bill.setDateCheckIn(rs.getTimestamp("dateCheckIn"));
                bill.setDateCheckOut(rs.getTimestamp("dateCheckOut"));
                bill.setTotalPrice(rs.getFloat("totalPrice"));
                bill.setDiscount(rs.getInt("discount"));
                bill.setStatus(true);
                return bill;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public int insertBill(int tableId) {
        int billId = -1;
        String sql = "INSERT INTO Bill(tableId, checkIn) VALUES(?, NOW())";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, tableId);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating bill failed, no rows affected.");
            }
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    billId = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return billId;
    }
    
    public boolean updateBill(int billId, int discount, float totalPrice) {
        String sql = "UPDATE Bill SET discount = ?, totalPrice = ?, checkOut = NOW() WHERE id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, discount);
            pstmt.setFloat(2, totalPrice);
            pstmt.setInt(3, billId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public int getNumBillListByDate(String date) {
        String sql = "SELECT COUNT(*) FROM Bill WHERE DATE(checkIn) = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, date);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                return rs.getInt(1);
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return 0;
    }
    
    public List<Bill> getBillListByDateAndPage(String date, int page) {
        int pageSize = 10;
        int offset = (page - 1) * pageSize;
        List<Bill> list = new ArrayList<>();
        String sql = "SELECT * FROM Bill WHERE DATE(checkIn) = ? LIMIT ? OFFSET ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, date);
            pstmt.setInt(2, pageSize);
            pstmt.setInt(3, offset);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setIdTable(rs.getInt("idTable"));
                bill.setDateCheckIn(rs.getTimestamp("dateCheckIn"));
                bill.setDateCheckOut(rs.getTimestamp("dateCheckOut"));
                bill.setTotalPrice(rs.getFloat("totalPrice"));
                bill.setDiscount(rs.getInt("discount"));
                bill.setStatus(rs.getBoolean("status"));
                list.add(bill);
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return list;
    }
    
    public List<Bill> getBillListByDate(String date) {
        List<Bill> list = new ArrayList<>();
        String sql = "SELECT * FROM Bill WHERE DATE(checkIn) = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, date);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setIdTable(rs.getInt("idTable"));
                bill.setDateCheckIn(rs.getTimestamp("dateCheckIn"));
                bill.setDateCheckOut(rs.getTimestamp("dateCheckOut"));
                bill.setTotalPrice(rs.getFloat("totalPrice"));
                bill.setDiscount(rs.getInt("discount"));
                bill.setStatus(rs.getBoolean("status"));
                list.add(bill);
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return list;
    }
    
    // Giả sử getMaxIdBill() trả về id cao nhất của Bill
    public int getMaxIdBill() {
        String sql = "SELECT MAX(id) FROM Bill";
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()){
                return rs.getInt(1);
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return -1;
    }
}
