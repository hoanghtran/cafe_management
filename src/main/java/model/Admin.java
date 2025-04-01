package model;

public class Admin extends Account {

    public Admin() {
        super();
    }

    public Admin(String username, String displayName, String password, int type) {
        super(username, displayName, password, type);
    }
    
    // Các phương thức quản lý tài khoản có thể được triển khai qua DAO.
    public void updateAccount() {
        // Gọi AccountDAO.updateAccount(this)
    }

    public Account getAccountByUserName(String username) {
        // Gọi AccountDAO.getAccountByUserName(username)
        return null;
    }

    public void insertAccount() {
        // Gọi AccountDAO.insertAccount(this)
    }

    public void deleteAccount() {
        // Gọi AccountDAO.deleteAccount(getUsername())
    }

    public void resetPassword() {
        // Gọi AccountDAO.resetPassword(getUsername(), "newPassword")
    }
}
