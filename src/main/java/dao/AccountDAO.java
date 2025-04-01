package dao;

import model.Account;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    public static Account login(String username, String password) {
        String sql = "SELECT * FROM Account WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Account(rs.getString("username"),
                            rs.getString("displayName"),
                            rs.getString("password"),
                            rs.getInt("type"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static boolean updateAccount(Account account) {
        String sql = "UPDATE Account SET displayName = ?, password = ?, type = ? WHERE username = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, account.getDisplayName());
            ps.setString(2, account.getPassword());
            ps.setInt(3, account.getType());
            ps.setString(4, account.getUsername());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static Account getAccountByUserName(String username) {
        String sql = "SELECT * FROM Account WHERE username = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Account(rs.getString("username"),
                            rs.getString("displayName"),
                            rs.getString("password"),
                            rs.getInt("type"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static boolean insertAccount(Account account) {
        String sql = "INSERT INTO Account (username, displayName, password, type) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getDisplayName());
            ps.setString(3, account.getPassword());
            ps.setInt(4, account.getType());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean deleteAccount(String username) {
        String sql = "DELETE FROM Account WHERE username = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static List<Account> getListAccount() {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM Account";
        try (Connection conn = DBConnection.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Account account = new Account(rs.getString("username"),
                        rs.getString("displayName"),
                        rs.getString("password"),
                        rs.getInt("type"));
                list.add(account);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static boolean resetPassword(String username, String newPassword) {
        String sql = "UPDATE Account SET password = ? WHERE username = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newPassword);
            ps.setString(2, username);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
