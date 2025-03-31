package dao;

import model.BillInfo;
import util.DatabaseConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillInfoDAO {
    public boolean insertBillInfo(BillInfo billInfo) {
        String sql = "INSERT INTO BillInfo(billId, foodId, count) VALUES(?,?,?)";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, billInfo.getBillId());
            pstmt.setInt(2, billInfo.getFoodId());
            pstmt.setInt(3, billInfo.getCount());
            return pstmt.executeUpdate() > 0;
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public List<BillInfo> getListBillInfo(int billId) {
        List<BillInfo> list = new ArrayList<>();
        String sql = "SELECT * FROM BillInfo WHERE billId = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, billId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                BillInfo info = new BillInfo(rs.getInt("id"), rs.getInt("billId"), rs.getInt("foodId"), rs.getInt("count"));
                list.add(info);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    public boolean deleteBillInfoByDrinkID(int drinkId) {
        String sql = "DELETE FROM BillInfo WHERE foodId = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, drinkId);
            return pstmt.executeUpdate() > 0;
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
