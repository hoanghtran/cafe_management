package model;

public class Account {

    private String username;
    private String displayName;
    private String password;
    private int type;

    public Account() {
    }

    public Account(String username, String displayName, String password, int type) {
        this.username = username;
        this.displayName = displayName;
        this.password = password;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
