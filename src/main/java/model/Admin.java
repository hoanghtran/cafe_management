package model;

import java.util.ArrayList;
import java.util.List;

public class Admin extends Account {
    public Admin() {
        super();
    }

    public Admin(String userName, String displayName, String password, int type) {
        super(userName, displayName, password, type);
    }
    
    // Các phương thức riêng cho Admin
    public void updateAccount(Account account) {
        // Cập nhật tài khoản
        System.out.println("Cập nhật tài khoản: " + account.getUserName());
    }
    
    public Account getAccountByUserName(String userName) {
        // Lấy tài khoản theo username (ví dụ trả về dummy)
        return new Account(userName, "Dummy Name", "password", 0);
    }
    
    public void insertAccount(Account account) {
        // Thêm tài khoản mới
        System.out.println("Thêm tài khoản: " + account.getUserName());
    }
    
    public void deleteAccount(String userName) {
        // Xóa tài khoản
        System.out.println("Xóa tài khoản: " + userName);
    }
    
    public List<Account> getListAccount() {
        // Lấy danh sách tài khoản (ví dụ dummy)
        List<Account> list = new ArrayList<>();
        list.add(new Account("staff1", "Nhân viên 1", "pass1", 0));
        list.add(new Account("staff2", "Nhân viên 2", "pass2", 0));
        return list;
    }
    
    public void resetPassword(String userName) {
        // Đặt lại mật khẩu cho tài khoản
        System.out.println("Đặt lại mật khẩu cho tài khoản: " + userName);
    }
}
