package model;

public class Account {
    private String userName;
    private String displayName;
    private String password;
    private int type; // 1: Admin, 0: Nhân viên

    public Account() { }

    public Account(String userName, String displayName, String password, int type) {
        this.userName = userName;
        this.displayName = displayName;
        this.password = password;
        this.type = type;
    }

    // Getters & Setters
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }

    // Các phương thức đăng nhập, đăng xuất
    public void login() {
        // Thông thường logic đăng nhập sẽ được thực hiện ở DAO
        System.out.println("Đăng nhập thành công cho " + displayName);
    }

    public void logout() {
        System.out.println("Đăng xuất thành công cho " + displayName);
    }
}
