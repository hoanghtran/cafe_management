package dao;

import model.Account;
import util.DatabaseConnector;
import java.sql.*;

public class AccountDAO {
    public Account login(String username, String password) {
        String sql = "SELECT * FROM Account WHERE Username = ? AND Password = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Account account = new Account();
                account.setUserName(rs.getString("Username"));
                account.setDisplayName(rs.getString("DisplayName"));
                account.setPassword(rs.getString("Password"));
                account.setType(rs.getInt("Type"));
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Bạn có thể thêm các phương thức khác như insertAccount, deleteAccount, updateAccount, resetPassword,...
}
