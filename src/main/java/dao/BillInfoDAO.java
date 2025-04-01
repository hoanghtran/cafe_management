package dao;

import model.BillInfo;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillInfoDAO {
    public static boolean insertBillInfo(BillInfo bi) {
        String sql = "INSERT INTO BillInfo (idBill, idFood, count) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bi.getIdBill());
            ps.setInt(2, bi.getIdFood());
            ps.setInt(3, bi.getCount());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public static List<BillInfo> getListBillInfo(int billId) {
        List<BillInfo> list = new ArrayList<>();
        String sql = "SELECT * FROM BillInfo WHERE idBill = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, billId);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    BillInfo bi = new BillInfo(rs.getInt("id"), rs.getInt("idBill"), rs.getInt("idFood"), rs.getInt("count"));
                    list.add(bi);
                }
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    public static boolean deleteBillInfoByFoodID(int foodId) {
        String sql = "DELETE FROM BillInfo WHERE idFood = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, foodId);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
