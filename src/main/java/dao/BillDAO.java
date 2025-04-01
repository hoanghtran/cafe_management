package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Bill;
import util.DBConnection;

public class BillDAO {

    // Lấy danh sách hóa đơn theo ngày sử dụng cột DateCheckIn (đã fix)
    public static List<Bill> getBillListByDate(Date date) {
        List<Bill> list = new ArrayList<>();
        // Bỏ hẳn WHERE để lấy tất cả Bill
        String sql = "SELECT * FROM Bill";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Bill bill = new Bill(rs.getInt("id"),
                            rs.getTimestamp("DateCheckIn"),
                            rs.getTimestamp("DateCheckOut"),
                            rs.getInt("idTable"),
                            rs.getInt("status"),
                            rs.getInt("discount"),
                            rs.getFloat("totalPrice"));
                    list.add(bill);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static int getNumBillListByDate(Date date) {
        String sql = "SELECT COUNT(*) AS count FROM Bill WHERE DATE(DateCheckIn) = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(date.getTime()));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("count");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static List<Bill> getBillListByDateAndPage(Date date, int page, int pageSize) {
        List<Bill> list = new ArrayList<>();
        String sql = "SELECT * FROM Bill WHERE DATE(DateCheckIn) = ? LIMIT ? OFFSET ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(date.getTime()));
            ps.setInt(2, pageSize);
            ps.setInt(3, (page - 1) * pageSize);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Bill bill = new Bill(rs.getInt("id"),
                            rs.getTimestamp("DateCheckIn"),
                            rs.getTimestamp("DateCheckOut"),
                            rs.getInt("idTable"),
                            rs.getInt("status"),
                            rs.getInt("discount"),
                            rs.getFloat("totalPrice"));
                    list.add(bill);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static boolean checkOut(Bill bill) {
        String sql = "UPDATE Bill SET DateCheckOut = ?, status = ?, discount = ?, totalPrice = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTimestamp(1, new Timestamp(bill.getDateCheckOut().getTime()));
            ps.setInt(2, bill.getStatus());
            ps.setInt(3, bill.getDiscount());
            ps.setFloat(4, bill.getTotalPrice());
            ps.setInt(5, bill.getId());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static int getMaxIdBill() {
        String sql = "SELECT MAX(id) AS maxId FROM Bill";
        try (Connection conn = DBConnection.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("maxId");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static boolean insertBill(Bill bill) {
        String sql = "INSERT INTO Bill (DateCheckIn, DateCheckOut, idTable, status, discount, totalPrice) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setTimestamp(1, new Timestamp(bill.getDateCheckIn().getTime()));
            if (bill.getDateCheckOut() != null) {
                ps.setTimestamp(2, new Timestamp(bill.getDateCheckOut().getTime()));
            } else {
                ps.setTimestamp(2, null);
            }
            ps.setInt(3, bill.getIdTable());
            ps.setInt(4, bill.getStatus());
            ps.setInt(5, bill.getDiscount());
            ps.setFloat(6, bill.getTotalPrice());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        bill.setId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static Bill getUnCheckBillByTableId(int tableId) {
        String sql = "SELECT * FROM Bill WHERE idTable = ? AND status = 0";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tableId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Bill(rs.getInt("id"),
                            rs.getTimestamp("DateCheckIn"),
                            rs.getTimestamp("DateCheckOut"),
                            rs.getInt("idTable"),
                            rs.getInt("status"),
                            rs.getInt("discount"),
                            rs.getFloat("totalPrice"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static boolean updateBillTotalPrice(int billId) {
        String sqlTotal = "SELECT SUM(d.price * bi.count) AS total FROM BillInfo bi JOIN Drink d ON bi.idFood = d.id WHERE bi.idBill = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sqlTotal)) {
            ps.setInt(1, billId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    float total = rs.getFloat("total");
                    String sqlUpdate = "UPDATE Bill SET totalPrice = ? WHERE id = ?";
                    try (PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate)) {
                        psUpdate.setFloat(1, total);
                        psUpdate.setInt(2, billId);
                        int rows = psUpdate.executeUpdate();
                        return rows > 0;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static Bill getBillById(int id) {
        String sql = "SELECT * FROM Bill WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Bill(rs.getInt("id"),
                            rs.getTimestamp("DateCheckIn"),
                            rs.getTimestamp("DateCheckOut"),
                            rs.getInt("idTable"),
                            rs.getInt("status"),
                            rs.getInt("discount"),
                            rs.getFloat("totalPrice"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
