package model;

public class Employee extends Account {
    public Employee() {
        super();
    }

    public Employee(String userName, String displayName, String password, int type) {
        super(userName, displayName, password, type);
    }
    
    // Các phương thức riêng cho nhân viên
    public void changeInfoAccount() {
        // Logic thay đổi thông tin cá nhân
        System.out.println("Nhân viên " + getDisplayName() + " thay đổi thông tin cá nhân.");
    }
    
    public void viewInfoAccount() {
        // Logic xem thông tin cá nhân
        System.out.println("Thông tin cá nhân của nhân viên: " + getDisplayName());
    }
}
