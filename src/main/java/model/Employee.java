package model;

public class Employee extends Account {

    public Employee() {
        super();
    }

    public Employee(String username, String displayName, String password, int type) {
        super(username, displayName, password, type);
    }
    
    public void changeInfoAccount() {
        // Gọi AccountDAO.updateAccount(this) hoặc logic tương ứng
    }
    
    public void viewInfoAccount() {
        // Có thể hiển thị thông tin cá nhân
    }
}
